package org.example.day11todobackend.service;

import org.example.day11todobackend.model.Todo;
import org.example.day11todobackend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
  private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo addTodoItem(Todo todo) {
        return  todoRepository.save(todo);
    }

    public Todo update(Integer id, Todo todo) {
        final Todo todoNeedToUpdate = todoRepository.findById(id).orElseThrow();
        todoNeedToUpdate.update(todo);
        return todoRepository.save(todoNeedToUpdate);
    }

    public void delete(Integer id) {
        todoRepository.deleteById(id);
    }

    public Todo findById(Integer id) {
        return todoRepository.findById(id).orElseThrow();
    }


}
