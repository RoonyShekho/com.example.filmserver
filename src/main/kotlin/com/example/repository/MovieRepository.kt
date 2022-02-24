package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Movie

interface MovieRepository {

    val movies:Map<Int, List<Movie>>

    val page1:List<Movie>
    val page2:List<Movie>
    val page3:List<Movie>
    val page4:List<Movie>
    val page5:List<Movie>



    suspend fun getAllMovies(page:Int = 1):ApiResponse

    suspend fun searchMovies(title:String?):ApiResponse
}