package com.example.jetflix.data.source.remote

import com.example.jetflix.data.api.MovieApi
import com.example.jetflix.data.mapper.MovieRequestOptionsMapper
import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.entities.MoviesResponse
import javax.inject.Inject

class MovieRemote @Inject constructor(
    private val movieApi: MovieApi,
    private val movieRequestOptionsMapper: MovieRequestOptionsMapper,
) {
    suspend fun fetchMovies(pageNumber: Int, filterState: FilterState?): MoviesResponse {
        val options = movieRequestOptionsMapper.map(filterState)
        return movieApi.fetchMovies(pageNumber, options)
    }

    suspend fun fetchGenre() = movieApi.fetchGenres()
}