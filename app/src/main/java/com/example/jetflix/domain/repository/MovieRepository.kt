package com.example.jetflix.domain.repository

import androidx.paging.PagingData
import com.example.jetflix.data.model.MovieModel
import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.entities.GenresResponse
import com.example.jetflix.domain.entities.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchGenre(): GenresResponse
    suspend fun fetchMovies(pageNumber: Int,filterState: FilterState?): MoviesResponse
    suspend fun getPagingSource() : Flow<PagingData<MovieModel>>
}