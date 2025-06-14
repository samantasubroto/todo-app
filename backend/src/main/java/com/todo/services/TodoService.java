package com.todo.services;

import com.todo.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    Todo createTodo(Todo todo);

    List<Todo> getAllTodos();

    Optional<Todo> getTodoById(Long id);

    Todo updateTodo(Long id, Todo todo);

    void deleteTodo(Long id);

}
