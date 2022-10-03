package com.example.jetflix.domain.repository

import com.example.jetflix.domain.entities.MovieEntity

interface MovieRepository {
    suspend fun getMovies(pageNumber: Int, queryKeyword: Map<String, String>): List<MovieEntity>
}