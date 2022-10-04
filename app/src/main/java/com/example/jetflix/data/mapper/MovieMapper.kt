package com.example.jetflix.data.mapper

import com.example.jetflix.data.model.MovieModel
import com.example.jetflix.domain.entities.MovieEntity
import com.example.jetflix.util.Mapper
import com.example.jetflix.util.toPosterUrl
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieEntity, MovieModel> {
    override fun map(input: MovieEntity) = MovieModel(
        id = input.id,
        name = input.name,
        releaseDate = input.firstAirDate.orEmpty(),
        posterPath = input.posterPath.orEmpty().toPosterUrl(),
        voteAverage = input.voteAverage,
        voteCount = input.voteCount
    )
}