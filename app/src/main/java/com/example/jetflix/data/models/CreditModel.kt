package com.example.jetflix.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsModel(
    @SerialName("id") val id: Int,
    @SerialName("cast") val cast: List<CastModel>,
    @SerialName("crew") val crew: List<CrewModel>
)

@Serializable
data class CastModel(
    @SerialName("cast_id") val castId: Int,
    @SerialName("character") val character: String,
    @SerialName("credit_id") val creditId: String,
    @SerialName("gender") val gender: Int,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("order") val order: Int,
    @SerialName("profile_path") val profilePath: String?
)

@Serializable
data class CrewModel(
    @SerialName("credit_id") val creditId: String,
    @SerialName("department") val department: String,
    @SerialName("gender") val gender: Int,
    @SerialName("id") val id: Int,
    @SerialName("job") val job: String,
    @SerialName("name") val name: String,
    @SerialName("profile_path") val profilePath: String?
)
