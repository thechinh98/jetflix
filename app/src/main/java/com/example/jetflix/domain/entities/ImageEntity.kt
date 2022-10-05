package com.example.jetflix.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesEntity(
    @SerialName("backdrops") val backdrops: List<ImageEntity>,
    @SerialName("id") val id: Int,
    @SerialName("posters") val posters: List<ImageEntity>
)

@Serializable
data class ImageEntity(
    @SerialName("aspect_ratio") val aspectRatio: Double,
    @SerialName("file_path") val filePath: String,
    @SerialName("height") val height: Int,
    @SerialName("iso_639_1") val iso6391: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("width") val width: Int
)