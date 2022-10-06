package com.example.jetflix.domain.entities

import kotlinx.serialization.Serializable

data class ImagesEntity(
    val backdrops: List<ImageEntity>,
    val id: Int,
    val posters: List<ImageEntity>
)

@Serializable
data class ImageEntity(
    val aspectRatio: Double,
    val filePath: String,
    val height: Int,
    val iso6391: String?,
   val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)