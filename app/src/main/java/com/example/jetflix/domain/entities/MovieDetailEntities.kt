package com.example.jetflix.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailEntity(
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("budget") val budget: Int,
    @SerialName("genres") val genres: List<GenreEntity>,
    @SerialName("homepage") val homepage: String?,
    @SerialName("id") val id: Int,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("overview") val overview: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanyEntity>,
    @SerialName("release_date") val releaseDate: String? = "",
    @SerialName("revenue") val revenue: Double,
    @SerialName("runtime") val runtime: Int?,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguageEntity>,
    @SerialName("status") val status: String,
    @SerialName("tagline") val tagline: String,
    @SerialName("title") val title: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)

@Serializable
data class ProductionCompanyEntity(
    @SerialName("id") val id: Int,
    @SerialName("logo_path") val logoPath: String?,
    @SerialName("name") val name: String,
    @SerialName("origin_country") val originCountry: String
)

@Serializable
data class SpokenLanguageEntity(
    @SerialName("iso_639_1") val iso6391: String,
    @SerialName("name") val name: String
)
