package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.MovieRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import java.lang.IllegalArgumentException

fun Route.getAllMovies(){

    val movieRepository :MovieRepository by inject()





    get("/movies"){
        try {
            val page = call.request.queryParameters["page"]?.toInt()?: 1

            require(page in 1..5)

            val apiResponse = movieRepository.getAllMovies(page)

            call.respond(
                message = apiResponse,
                status= HttpStatusCode.OK
            )

        }catch (e:NumberFormatException){
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Numbers only are allowed"),
                status = HttpStatusCode.BadRequest
            )
        }catch (e:IllegalArgumentException){
            call.respond(
                message = ApiResponse(false, "Not found"),
                status = HttpStatusCode.NotFound
            )
        }
    }


    get("/films"){
     /*   try {
            val page = call.request.queryParameters["page"]?.toInt()?: 1

            require(page in 1..5)
            
            val apiResponse = movieRepository.getAllMovies(page)

            call.respond(
                message = apiResponse,
                status= HttpStatusCode.OK
            )

        }catch (e:NumberFormatException){
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Numbers only are allowed"),
                    status = HttpStatusCode.BadRequest
            )
        }catch (e:IllegalArgumentException){
            call.respond(
                message = ApiResponse(false, "Not found"),
                status = HttpStatusCode.NotFound
            )
        }*/
    }
}