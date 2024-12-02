package org.example.day11todobackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    private boolean done;

    public Todo(Integer id, String text, boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public Todo() {

    }

    public void update(Todo todo) {
        if (todo.getText() != null) {
            this.setText(todo.getText());
        }
        if (todo.isDone() != this.isDone()) {
            this.setDone(todo.isDone());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
