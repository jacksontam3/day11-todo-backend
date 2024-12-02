package org.example.day11todobackend.service;

import org.example.day11todobackend.model.Todo;
import org.example.day11todobackend.repository.TodoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class TodoServiceTest {


    @Test
    void should_return_todolist_when_findAll_given_request() {
        // Given
        TodoRepository mockTodoRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService(mockTodoRepository);

        List<Todo> expectedTodos = List.of(new Todo(1,"Don", FALSE), new Todo(2, "ZANE", FALSE));
        when(mockTodoRepository.findAll()).thenReturn(expectedTodos);

        // When
        List<Todo> actualTodos = todoService.findAll();

        // Then
        assertEquals(expectedTodos, actualTodos);
    }

    @Test
    void addTodoItem() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void should_return_the_given_todos_when_findById() {
        //given
        TodoRepository mockTodoRepository = mock(TodoRepository.class);
        TodoService todoService = new TodoService(mockTodoRepository);
        Todo expectedTodos = new Todo(1, "abc", false);
                when(mockTodoRepository.findById(1)).thenReturn(Optional.of(expectedTodos));
        //when
        Todo actualTodos = todoService.findById(1);
        //then
        assertEquals(expectedTodos, actualTodos);
    }

    @Test
    void should_return_the_created_todo_when_create_given_a_todo() {
        //given
        TodoRepository mockTodoRepository = mock(TodoRepository.class);

        Todo expected_todo = new Todo(1, "abc", false);
        when(mockTodoRepository.save(expected_todo)).thenReturn(expected_todo);
        TodoService todoService = new TodoService(mockTodoRepository);

        //when
        Todo createdTodo = todoService.addTodoItem(expected_todo);

        //then
        assertEquals(expected_todo, createdTodo);
    }

    @Test
    void should_return_update_todo_when_update_todo() {
        //given
        TodoRepository mockTodoRepository = mock(TodoRepository.class);

        TodoService todoService = new TodoService(mockTodoRepository);

        Todo todo = new Todo(1, "abc", false);
        Todo expected_todo = new Todo(1, "def", true);
        when(mockTodoRepository.save(todo)).thenReturn(expected_todo);

        //when
        Todo createdTodo = todoService.addTodoItem(todo);

        //then
        assertEquals(expected_todo, createdTodo);
    }





}