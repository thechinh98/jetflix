package com.example.jetflix.domain.usecase.movie

import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieFlowUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(filterState: FilterState?) = movieRepository.getPagingSource(filterState)
}