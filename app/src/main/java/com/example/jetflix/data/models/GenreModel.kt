package com.example.jetflix.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresModel(@SerialName("genres") val genreModels: List<GenreModel>)

@Serializable
data class GenreModel(@SerialName("id") val id: Int, @SerialName("name") val name: String?)