package com.example.jetflix.domain.usecase.movie

import com.example.jetflix.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(pageNumber: Int, searchWord: String) =
        movieRepository.search(pageNumber, searchWord)
}