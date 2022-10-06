package com.example.jetflix.domain.repository

import androidx.paging.PagingData
import com.example.jetflix.data.models.FilterState
import com.example.jetflix.domain.entities.GenreEntity
import com.example.jetflix.domain.entities.MovieEntities
import com.example.jetflix.domain.entities.MoviesResponseEntities
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchGenre(): List<GenreEntity>
    suspend fun fetchMovies(pageNumber: Int, filterState: FilterState?): MoviesResponseEntities
    fun getPagingSource(filterState: FilterState?): Flow<PagingData<MovieEntities>>
}