package com.todolist.spring.controllers;

import com.estebandcprojects.models.TodoTask;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final List<TodoTask> todoTaskList = new ArrayList<>();
    private int lastId = 2;
    
    public TodoService() {
        todoTaskList.add(new TodoTask(1, "My first Todo", "I need to do this", false, "2023-01-01"));
        todoTaskList.add(new TodoTask(2, "My second Todo", "I need to do this", false, "2023-01-01"));
    }
    
    public List<TodoTask> getTodoList() {
        return new ArrayList<>(todoTaskList);
    }
    
    public Optional<TodoTask> getTodoById(int id) {
        return todoTaskList.stream()
                .filter(task -> task.getId() == id)
                .findFirst();
    }
    
    public void completeTask(int id, boolean completed) {
        getTodoById(id).ifPresent(task -> task.setCompleted(completed));
    }
    
    public void deleteTask(int id) {
        getTodoById(id).ifPresent(todoTaskList::remove);
    }
    
    public TodoTask createTask(TodoTask task) {
        TodoTask newTask = new TodoTask(
                ++lastId,
                task.getTitle(),
                task.getDescription(),
                task.getCompleted(),
                task.getDueDate()
        );
        todoTaskList.add(newTask);
        return newTask;
    }
}
