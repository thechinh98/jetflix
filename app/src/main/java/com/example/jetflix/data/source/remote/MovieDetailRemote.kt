package com.example.jetflix.data.source.remote

import com.example.jetflix.data.api.MovieApi
import com.example.jetflix.data.models.CreditsModel
import com.example.jetflix.data.models.ImagesModel
import com.example.jetflix.data.models.MovieDetailModel
import javax.inject.Inject

class MovieDetailRemote @Inject constructor(
    private val movieApi: MovieApi
) {
    suspend fun fetchMovieDetail(movieId: Int) : MovieDetailModel {
        return movieApi.fetchMovieDetail(movieId)
    }

    suspend fun fetchMovieCredits(movieId: Int): CreditsModel {
        return movieApi.fetchMovieCredits(movieId)
    }
    suspend fun fetchMovieImages(movieId: Int) : ImagesModel {
        return movieApi.fetchMovieImages(movieId)
    }
}