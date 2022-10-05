package com.example.jetflix.domain.usecase.moviedetail

import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    val fetchMovieCreditUseCase: FetchMovieCreditUseCase,
    val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    val fetchMovieImage: FetchMovieImage
) {
}