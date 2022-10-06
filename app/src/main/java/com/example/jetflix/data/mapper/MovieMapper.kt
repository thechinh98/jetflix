package com.example.jetflix.data.mapper

import com.example.jetflix.data.models.MovieModel
import com.example.jetflix.util.Mapper
import com.example.jetflix.domain.entities.MovieEntities
import com.example.jetflix.util.toPosterUrl
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieModel, MovieEntities> {
    override fun map(input: MovieModel) = MovieEntities(
        id = input.id,
        name = input.name,
        releaseDate = input.firstAirDate.orEmpty(),
        posterPath = input.posterPath.orEmpty().toPosterUrl(),
        voteAverage = input.voteAverage,
        voteCount = input.voteCount
    )
}