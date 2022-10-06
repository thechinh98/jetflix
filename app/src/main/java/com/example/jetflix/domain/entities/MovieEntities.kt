package com.example.jetflix.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class MovieEntities(
    val id: Int,
    val name: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)