package org.example.day11todobackend.controller;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.example.day11todobackend.model.Todo;
import org.example.day11todobackend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class TodoControllerTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private JacksonTester<List<Todo>> todosResponseEntityJacksonTester;


    @Autowired
    private JacksonTester<Todo> todoJacksonTester;

    @BeforeEach
    void setUp() {
        givenDataToJpaRepository();
    }

    private void givenDataToJpaRepository() {
        todoRepository.deleteAll();
        todoRepository.save(new Todo(null, "abc", false));
        todoRepository.save(new Todo(null, "def", true));
        todoRepository.save(new Todo(null, "hij", false));
        todoRepository.save(new Todo(null, "kln", true));
        todoRepository.save(new Todo(null, "mop", false));
    }

    @Test
    void should_return_todos_when_get_all_given_todo_exist() throws Exception {
        //given
        final List<Todo> givenTodos = todoRepository.findAll();

        //when
        //then
        final String jsonResponse = client.perform(MockMvcRequestBuilders.get("/TodoItem"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        final List<Todo> todosResult = todosResponseEntityJacksonTester.parseObject(jsonResponse);
        assertThat(todosResult)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(givenTodos);
    }

    @Test
    void should_return_todo_when_get_by_id() throws Exception {
        // Given
        final Todo givenTodo = todoRepository.findAll().get(0);

        // When
        // Then
        client.perform(MockMvcRequestBuilders.get("/TodoItem/" + givenTodo.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(givenTodo.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value(givenTodo.getText()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.done").value(givenTodo.isDone()));
    }

    @Test
    void should_create_todo_success() throws Exception {
        // Given
        String givenTodoJson = "{\"text\": \"PPP\", \"done\": false}";

        // When
        String contentAsString = client.perform(MockMvcRequestBuilders.post("/TodoItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(givenTodoJson)
        ).andReturn().getResponse().getContentAsString();

        // Then
        Todo todo = todoJacksonTester.parseObject(contentAsString);
        assertThat(todo.getText()).isEqualTo("PPP");
        assertThat(todo.isDone()).isFalse();
    }

    @Test
    void should_update_todo_success() throws Exception {
        // Given
        Todo givenTodo = new Todo();
        givenTodo.setText("PPP");
        givenTodo.setDone(false);
        givenTodo = todoRepository.save(givenTodo);

        String givenTodoString = "{\"id\": " + givenTodo.getId() + ", \"text\": \"PPP\", \"done\": true}";

        // When
        String contentAsString = client.perform(MockMvcRequestBuilders.put("/TodoItem/" + givenTodo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(givenTodoString))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        Todo updatedTodo = todoRepository.findById(givenTodo.getId()).orElseThrow();
        Assertions.assertThat(updatedTodo.isDone()).isTrue();
        AssertionsForClassTypes.assertThat(updatedTodo.getId()).isEqualTo(givenTodo.getId());
        AssertionsForClassTypes.assertThat(updatedTodo.getText()).isEqualTo("PPP");

    }

}