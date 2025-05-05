package com.todolist.spring.controllers;

import com.estebandcprojects.models.TodoTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    void getTodoList() {
        var todoList = todoService.getTodoList();
        assertNotNull(todoList);
        assertEquals(2, todoList.size());
    }

    @Test
    void getTodoById() {
        var todoOptional = todoService.getTodoById(1);
        assertTrue(todoOptional.isPresent());
        assertEquals("My first Todo", todoOptional.get().getTitle());
    }

    @Test
    void completeTask() {
        todoService.completeTask(1, true);
        var todoOptional = todoService.getTodoById(1);
        assertTrue(todoOptional.isPresent());
        assertTrue(todoOptional.get().getCompleted());
    }

    @Test
    void createTask() {
        var newTask = new TodoTask(0, "Test Task", "Test Description", false, "2023-01-01");
        var createdTask = todoService.createTask(newTask);
        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getTitle());
        assertEquals(3, createdTask.getId());
        
        var todoList = todoService.getTodoList();
        assertEquals(3, todoList.size());
    }

    @Test
    void deleteTask() {
        todoService.deleteTask(2);
        var todoOptional = todoService.getTodoById(2);
        assertFalse(todoOptional.isPresent());
        
        var todoList = todoService.getTodoList();
        assertEquals(2, todoList.size());
    }
}