package com.todo.services.impl;

import com.todo.entity.Todo;
import com.todo.repository.TodoRepository;
import com.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultTodoService implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (optionalTodo.isPresent()) {
            Todo existingTodo = optionalTodo.get();
            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setDescription(updatedTodo.getDescription());
            existingTodo.setCompleted(updatedTodo.isCompleted());
            // updatedAt will be handled by @PreUpdate in the entity
            return todoRepository.save(existingTodo);
        } else {
            throw new RuntimeException("Todo with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo with ID " + id + " not found.");
        }
        todoRepository.deleteById(id);
    }
}
