package com.example.jetflix.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    val id: Int,
    val name: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)