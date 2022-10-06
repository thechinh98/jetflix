package com.example.jetflix.domain.entities

import com.example.jetflix.data.models.SortByEnum
import com.example.jetflix.data.models.SortOrderEnum
import com.example.jetflix.presentation.screens.filter.option.*

data class FilterStateEntity(
    val sortOrder: SortOrderEnum,
    val sortBy: SortByEnum,
    val includeAdult: Boolean,
    val selectedGenreIds: List<Int>,
    val genres: List<GenreEntity>
)

fun FilterStateEntity.toFilterOptions(): List<FilterOption<*>> = buildList {
    add(SortOrderOption(sortOrder))
    add(SortByOption(sortBy))
    if (genres.isNotEmpty()) {
        add(GenresOption(GenresFilterOption(genres, selectedGenreIds.toMutableList())))
    }
    add(IncludeAdultOption(includeAdult))
}