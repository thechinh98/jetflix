package com.example.jetflix.data.mapper

import com.example.jetflix.data.models.FilterState
import com.example.jetflix.domain.entities.FilterStateEntity
import com.example.jetflix.util.Mapper
import javax.inject.Inject

class FilterStateMapper @Inject constructor(
    private val genreMapper: GenreMapper
) : Mapper<FilterState, FilterStateEntity> {
    override fun map(input: FilterState): FilterStateEntity {
        return FilterStateEntity(
            sortOrder = input.sortOrder,
            sortBy = input.sortBy,
            includeAdult = input.includeAdult,
            selectedGenreIds = input.selectedGenreIds,
            genres = input.genres.map { genreMapper.map(it) }
        )
    }

    fun reverseMap(input: FilterStateEntity?): FilterState? {
        if(input == null){
            return null
        }
        return FilterState(
            sortOrder = input.sortOrder,
            sortBy = input.sortBy,
            includeAdult = input.includeAdult,
            selectedGenreIds = input.selectedGenreIds,
            genres = input.genres.map { genreMapper.reverseMap(it) }
        )
    }

}