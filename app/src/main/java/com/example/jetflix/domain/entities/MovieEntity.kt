package com.example.jetflix.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val movies: List<MovieEntity>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)

data class MovieEntity(
    @SerialName("id") val id: Int,
    @SerialName("release_date") val firstAirDate: String? = "",
    @SerialName("title") val name: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)