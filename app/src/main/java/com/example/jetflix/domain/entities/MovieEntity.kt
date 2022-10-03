package com.example.jetflix.domain.entities

import com.example.jetflix.data.model.Genre

data class MovieEntity (
    val id: Int,
    val name: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)