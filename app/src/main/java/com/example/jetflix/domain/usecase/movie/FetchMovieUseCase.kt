package com.example.jetflix.domain.usecase.movie

import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(pageNumber: Int, filterState: FilterState?) =
        movieRepository.fetchMovies(pageNumber, filterState)

}