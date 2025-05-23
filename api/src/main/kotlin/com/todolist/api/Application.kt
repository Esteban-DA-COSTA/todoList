package com.todolist.api

import com.todolist.api.routing.configureTodoRouting
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    install(CORS) {
        anyHost()
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Delete)
        allowHeader(HttpHeaders.ContentType)
    }
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
