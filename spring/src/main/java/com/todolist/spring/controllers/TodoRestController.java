package com.todolist.spring.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estebandcprojects.models.TodoTask;

@RestController
@RequestMapping("/tasks")
public class TodoRestController {

    private final TodoService todoService;

    public TodoRestController(TodoService todoService) {this.todoService = todoService;}
    
    @GetMapping
    public List<TodoTask> getAllTasks() {
        return todoService.getTodoList();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable int id) {
        var taskOptional = todoService.getTodoById(id);
        if (taskOptional.isPresent()) {
            return ResponseEntity.ok(taskOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found");
        }
    }
    
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable int id, @RequestBody Map<String, Boolean> payload) {
        Boolean completed = payload.getOrDefault("completed", true);
        todoService.completeTask(id, completed);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        todoService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Create a new task
     */
    @PostMapping
    public ResponseEntity<TodoTask> createTask(@RequestBody TodoTask task) {
        TodoTask createdTask = todoService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
}
