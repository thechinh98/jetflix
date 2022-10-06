package com.example.jetflix.domain.entities

data class MoviesResponseEntities(
    val page: Int,
    val movies: List<MovieEntity>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieEntity(
    val id: Int,
    val firstAirDate: String? = "",
    val name: String,
    val originalTitle: String,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int
)