package com.todolist.api.routing

import com.estebandcprojects.models.TodoTask
import com.todolist.api.controllers.TodoController
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonPrimitive

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
            val payload = call.receive<JsonObject>()
            val completed: Boolean = payload["completed"]?.jsonPrimitive?.boolean ?: true
            controller.completeTask(id, completed)
            return@patch call.respond(HttpStatusCode.NoContent)
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
            controller.deleteTask(id)
            return@delete call.respond(HttpStatusCode.NoContent)
        }
        post {
            val newTask = call.receive<TodoTask>()
            val taskCreated = controller.createTask(newTask)
            return@post call.respond(HttpStatusCode.Created, taskCreated)
        }
    }
}
