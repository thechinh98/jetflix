package com.example.jetflix.domain.entities


data class MovieDetailEntities(
    val id: Int,
    val title: String = "",
    val originalTitle: String = "",
    val tagline: String = "",
    val overview: String = "",
    val backdropUrl: String = "",
    val posterUrl: String = "",
    val genres: List<GenreEntity> = emptyList(),
    val releaseDate: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val duration: Int = 0,
    val productionCompanies: List<ProductionCompanyEntities> = emptyList(),
    val homepage: String? = null
)

data class ProductionCompanyEntities(val name: String, val logoUrl: String)
