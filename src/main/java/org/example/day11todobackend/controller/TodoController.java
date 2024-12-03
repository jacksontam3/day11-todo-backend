package org.example.day11todobackend.controller;


import org.example.day11todobackend.common.ResponseEntity;
import org.example.day11todobackend.model.Todo;
import org.example.day11todobackend.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TodoItem")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodoList() {
        return ResponseEntity.success(todoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Todo> addTodoItem(@RequestBody Todo todo) {
        return ResponseEntity.success(todoService.addTodoItem(todo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoItem(@PathVariable Integer id, @RequestBody Todo todo) {
        return ResponseEntity.success(todoService.update(id, todo));
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable Integer id) {
        todoService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoItemById(@PathVariable Integer id) {
        return ResponseEntity.success(todoService.findById(id));
    }


}
