package com.example.jetflix.data.repository

import com.example.jetflix.data.mapper.CreditsMapper
import com.example.jetflix.data.mapper.ImageMapper
import com.example.jetflix.data.mapper.MovieDetailMapper
import com.example.jetflix.data.source.remote.MovieDetailRemote
import com.example.jetflix.domain.entities.CreditsEntities
import com.example.jetflix.domain.entities.ImageEntities
import com.example.jetflix.domain.entities.MovieDetailEntities
import com.example.jetflix.domain.repository.MovieDetailRepository
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDetailRemote: MovieDetailRemote,
    private val movieDetailMapper: MovieDetailMapper,
    private val creditsMapper: CreditsMapper,
    private val imageMapper: ImageMapper
) : MovieDetailRepository {
    override suspend fun fetchMovieDetail(movieId: Int): MovieDetailEntities {
        val result = movieDetailRemote.fetchMovieDetail(movieId)
        return movieDetailMapper.map(result)
    }

    override suspend fun fetchMovieCredit(movieId: Int): CreditsEntities {
        val result = movieDetailRemote.fetchMovieCredits(movieId)
        return creditsMapper.map(result)
    }

    override suspend fun fetchMovieImages(movieId: Int): List<ImageEntities> {
        val result = movieDetailRemote.fetchMovieImages(movieId)
        return imageMapper.map(result)
    }

}