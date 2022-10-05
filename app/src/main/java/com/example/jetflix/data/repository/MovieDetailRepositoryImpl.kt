package com.example.jetflix.data.repository

import com.example.jetflix.data.source.remote.MovieDetailRemote
import com.example.jetflix.domain.entities.CreditsEntity
import com.example.jetflix.domain.entities.ImagesEntity
import com.example.jetflix.domain.entities.MovieDetailEntity
import com.example.jetflix.domain.repository.MovieDetailRepository
import com.example.jetflix.presentation.mapper.MovieDetailMapper
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDetailRemote: MovieDetailRemote,
    private val movieDetailMapper: MovieDetailMapper
) : MovieDetailRepository {
    override suspend fun fetchMovieDetail(movieId: Int): MovieDetailEntity {
        val result = movieDetailRemote.fetchMovieDetail(movieId)
        return movieDetailRemote.fetchMovieDetail(movieId)
    }

    override suspend fun fetchMovieCredit(movieId: Int): CreditsEntity {
        return movieDetailRemote.fetchMovieCredits(movieId)
    }

    override suspend fun fetchMovieImages(movieId: Int): ImagesEntity {
        return movieDetailRemote.fetchMovieImages(movieId)
    }

}