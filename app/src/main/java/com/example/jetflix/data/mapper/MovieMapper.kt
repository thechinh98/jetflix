package com.example.jetflix.data.mapper

import com.example.jetflix.data.model.MovieResponse
import com.example.jetflix.domain.entities.MovieEntity
import com.example.jetflix.util.Mapper
import com.example.jetflix.util.toPosterUrl
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieResponse, MovieEntity> {
    override fun map(input: MovieResponse) = MovieEntity(
        id = input.id,
        name = input.name,
        releaseDate = input.firstAirDate.orEmpty(),
        posterPath = input.posterPath.orEmpty().toPosterUrl(),
        voteAverage = input.voteAverage,
        voteCount = input.voteCount
    )
}