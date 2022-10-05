package com.example.jetflix.domain.usecase.moviedetail

import com.example.jetflix.domain.repository.MovieDetailRepository
import javax.inject.Inject

class FetchMovieCreditUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) {
    suspend operator fun invoke(movieId: Int) = movieDetailRepository.fetchMovieCredit(movieId)
}