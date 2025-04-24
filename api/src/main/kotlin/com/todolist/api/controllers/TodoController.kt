package com.todolist.api.controllers

import com.estebandcprojects.models.TodoTask

class TodoController() {
    private val _todoTaskList = mutableListOf<TodoTask>()
    val todoList get() = _todoTaskList.toList()

    init {
        _todoTaskList.addAll(
            listOf(
                TodoTask(1, "My first Todo", "I need to do this", false, "2023-01-01"),
                TodoTask(2, "My second Todo", "I need to do this", false, "2023-01-01"),
            )
        )
    }

    fun getTodoById(id: Int) = _todoTaskList.find { it.id == id }
    
    fun completeTask(id: Int, completed: Boolean = true) {
        val task = getTodoById(id) ?: return
        task.completed = completed
    }
}