package com.example.jetflix.domain.repository

import com.example.jetflix.domain.entities.CreditsEntities
import com.example.jetflix.domain.entities.ImageEntities
import com.example.jetflix.domain.entities.MovieDetailEntities


interface MovieDetailRepository {
    suspend fun fetchMovieDetail(movieId: Int): MovieDetailEntities
    suspend fun fetchMovieCredit(movieId: Int): CreditsEntities
    suspend fun fetchMovieImages(movieId: Int): List<ImageEntities>
}