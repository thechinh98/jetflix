package com.example.jetflix.presentation.screens.filter.option

import androidx.compose.runtime.Composable
import com.example.jetflix.domain.entities.FilterState

interface FilterOption<Type : Any> {
    val defaultValue: Type
    var currentValue: Type

    fun modifyFilterState(filterState: FilterState): FilterState

    @Composable
    fun Render(onChanged: () -> Unit)
}


fun FilterState.toFilterOptions(): List<FilterOption<*>> = buildList {
    add(SortOrderOption(sortOrder))
    add(SortByOption(sortBy))
    if (genres.isNotEmpty()) {
        add(GenresOption(GenresFilterOption(genres.map{GenreUiModel(it)}, selectedGenreIds.toMutableList())))
    }
    add(IncludeAdultOption(includeAdult))
}