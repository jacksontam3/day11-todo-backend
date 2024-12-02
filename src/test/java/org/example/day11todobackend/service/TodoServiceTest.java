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
    void findById() {
    }
}