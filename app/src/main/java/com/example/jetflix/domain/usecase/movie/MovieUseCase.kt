package com.example.jetflix.domain.usecase.movie

import javax.inject.Inject

data class MovieUseCase @Inject constructor(
    val fetchMovieUseCase: FetchMovieUseCase,
    val searchMovieUseCase: SearchMovieUseCase,
    val getMovieFlowUseCase: GetMovieFlowUseCase
) {
}