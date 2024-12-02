package org.example.day11todobackend.exception;

public class TodoItemNotFoundException extends RuntimeException {
    public TodoItemNotFoundException() {
        super("Todo item not found");
    }
}