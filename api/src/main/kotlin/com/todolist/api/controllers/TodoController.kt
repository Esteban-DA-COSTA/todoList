package com.todolist.api.controllers

import com.estebandcprojects.models.TodoTask

class TodoController() {
    private val _todoTaskList = mutableListOf<TodoTask>()
    val todoList get() = _todoTaskList.toList()

    init {
        _todoTaskList.addAll(
            listOf(
                TodoTask(1, "My first Todo"),
                TodoTask(2, "My second Todo"),
            )
        )
    }

    fun getTodoById(id: Int) = _todoTaskList.find { it.id == id }
}