package com.todolist.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estebandcprojects.models.TodoTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].title", is("My first Todo")))
                .andExpect(jsonPath("$[1].title", is("My second Todo")));
    }

    @Test
    void getTaskById() throws Exception {
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("My first Todo")));

        mockMvc.perform(get("/tasks/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void completeTask() throws Exception {
        Map<String, Boolean> payload = new HashMap<>();
        payload.put("completed", true);

        mockMvc.perform(patch("/tasks/1/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    void createTask() throws Exception {
        TodoTask newTask = new TodoTask(0, "Test Task", "Test Description", false, "2023-01-01");

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Test Task")))
                .andExpect(jsonPath("$.id", greaterThan(0)));
    }

    @Test
    void deleteTask() throws Exception {
        // First, create a task to delete
        TodoTask newTask = new TodoTask(0, "Task to Delete", "Will be deleted", false, "2023-01-01");

        String response = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        TodoTask createdTask = objectMapper.readValue(response, TodoTask.class);

        // Then delete it
        mockMvc.perform(delete("/tasks/" + createdTask.getId()))
                .andExpect(status().isNoContent());

        // Verify it's gone
        mockMvc.perform(get("/tasks/" + createdTask.getId()))
                .andExpect(status().isNotFound());
    }
}