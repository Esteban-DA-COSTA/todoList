package com.todolist.api.routing

import com.todolist.api.controllers.TodoController
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.route

fun Routing.configureTodoRouting() {
    val controller = TodoController()
    route("/tasks") {
        get {
            val response = call.respond(controller.todoList)
            return@get response
            
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get
            controller.getTodoById(id)?.let { 
                return@get call.respond(it)
            }
            return@get call.respond(HttpStatusCode.NotFound, "Todo not found")
        }
        patch("/{id}/complete") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@patch call.respond(HttpStatusCode.BadRequest)
            val completed = call.parameters["completed"]?.toBooleanStrictOrNull() ?: true
            controller.completeTask(id, completed)
            return@patch call.respond(HttpStatusCode.NoContent)
        }
    }
}
