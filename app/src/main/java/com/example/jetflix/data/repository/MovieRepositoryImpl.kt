package com.example.jetflix.data.repository

import com.example.jetflix.data.api.MovieApi
import com.example.jetflix.domain.entities.MovieEntity
import com.example.jetflix.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {
    override suspend fun getMovies(
        pageNumber: Int,
        queryKeyword: Map<String, String>
    ): List<MovieEntity> {
        movieApi.fetchMovies(pageNumber, queryKeyword)
    }
}