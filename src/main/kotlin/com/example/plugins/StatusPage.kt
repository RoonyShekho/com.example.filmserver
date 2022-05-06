package com.example.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.configureStatusPages(){
    install(StatusPages){
        status(HttpStatusCode.NotFound){
            call.respond(
                message = "Roony not found",
                status = HttpStatusCode.NotFound
            )
        }

    }
}