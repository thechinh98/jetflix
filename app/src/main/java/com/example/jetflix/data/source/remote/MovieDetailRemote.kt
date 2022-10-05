package com.example.jetflix.data.source.remote

import com.example.jetflix.data.api.MovieApi
import com.example.jetflix.domain.entities.CreditsEntity
import com.example.jetflix.domain.entities.ImagesEntity
import com.example.jetflix.domain.entities.MovieDetailEntity
import javax.inject.Inject

class MovieDetailRemote @Inject constructor(
    private val movieApi: MovieApi
) {
    suspend fun fetchMovieDetail(movieId: Int) : MovieDetailEntity{
        return movieApi.fetchMovieDetail(movieId)
    }

    suspend fun fetchMovieCredits(movieId: Int): CreditsEntity{
        return movieApi.fetchMovieCredits(movieId)
    }
    suspend fun fetchMovieImages(movieId: Int) : ImagesEntity{
        return movieApi.fetchMovieImages(movieId)
    }
}