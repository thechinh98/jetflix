package com.example.jetflix.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class FilterState(
    @SerialName("sort_order") val sortOrder: SortOrder = SortOrder.DESCENDING,
    @SerialName("sort_by") val sortBy: SortBy = SortBy.POPULARITY,
    @SerialName("includeAdult") val includeAdult: Boolean = false,
    @SerialName("selected_genre_ids") val selectedGenreIds: List<Int> = emptyList(),
    @Transient val genres: List<GenreEntity> = emptyList()
)