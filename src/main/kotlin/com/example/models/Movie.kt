package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id:Int,
    val title:String,
    val image:String,
    val plot:String,
    val rating:Double,
)
