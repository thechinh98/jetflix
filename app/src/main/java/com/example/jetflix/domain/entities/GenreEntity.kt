package com.example.jetflix.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(@SerialName("genres") val genreEntities: List<GenreEntity>)

@Serializable
data class GenreEntity(@SerialName("id") val id: Int, @SerialName("name") val name: String?)