package com.example.jetflix.domain.usecase.genre

import com.example.jetflix.domain.repository.MovieRepository
import javax.inject.Inject


class FetchGenreUseCase @Inject constructor(private val moviesRepository: MovieRepository) {
    suspend operator fun invoke() = moviesRepository.fetchGenre()
}