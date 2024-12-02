package org.example.day11todobackend.controller;


import org.example.day11todobackend.model.Todo;
import org.example.day11todobackend.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/TodoItem")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodoList() {
        return todoService.findAll();
    }

    @PostMapping
    public Todo addTodoItem(@RequestBody Todo todo) {
        return todoService.addTodoItem(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodoItem(@PathVariable Integer id, @RequestBody Todo todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable Integer id) {
        todoService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoItemById(@PathVariable Integer id) {
        try {
            Todo todo = todoService.findById(id);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
