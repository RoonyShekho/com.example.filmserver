package com.example.plugins

import com.example.routes.getAllMovies
import com.example.routes.root
import com.example.routes.searchMovies
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.content.*

fun Application.configureRouting() {

    routing {
        root()
        getAllMovies()
        searchMovies()

        static("/images"){
            resources("images")
        }
    }
}
