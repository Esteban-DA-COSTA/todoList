package com.todolist.api

import com.todolist.api.routing.configureTodoRouting
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
      json()
    }
    routing {
        get("/") {
            call.respondText("Hello, Ktor!")
        }
        configureTodoRouting()
    }
}
