package com.example.jetflix.domain.repository

import com.example.jetflix.domain.entities.CreditsEntity
import com.example.jetflix.domain.entities.ImagesEntity
import com.example.jetflix.domain.entities.MovieDetailEntity


interface MovieDetailRepository {
    suspend fun fetchMovieDetail(movieId: Int): MovieDetailEntity
    suspend fun fetchMovieCredit(movieId: Int): CreditsEntity
    suspend fun fetchMovieImages(movieId: Int): ImagesEntity
}