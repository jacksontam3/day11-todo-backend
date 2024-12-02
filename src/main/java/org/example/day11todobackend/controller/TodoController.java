package org.example.day11todobackend.controller;


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
    public List<Todo> getTodoList() {
        return todoService.findAll();
    }

    @PostMapping
    public Todo addTodoItem(@RequestBody Todo todo) {
        return todoService.addTodoItem(todo);
    }

}
