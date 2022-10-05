package com.example.jetflix.domain.usecase.moviedetail

import com.example.jetflix.domain.repository.MovieDetailRepository
import javax.inject.Inject

class FetchMovieImage @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) {
    suspend operator fun invoke(movieId: Int) = movieDetailRepository.fetchMovieImages(movieId)
}