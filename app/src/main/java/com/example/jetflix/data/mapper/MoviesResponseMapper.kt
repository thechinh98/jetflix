package com.example.jetflix.data.mapper

import com.example.jetflix.data.models.MovieModel
import com.example.jetflix.data.models.MoviesModel
import com.example.jetflix.domain.entities.MovieEntity
import com.example.jetflix.domain.entities.MoviesResponseEntities
import com.example.jetflix.util.Mapper
import javax.inject.Inject

class MoviesResponseMapper @Inject constructor(
    private val movieEntityMapper: MovieEntityMapper
) : Mapper<MoviesModel, MoviesResponseEntities> {
    override fun map(input: MoviesModel): MoviesResponseEntities {
        return MoviesResponseEntities(
            page = input.page,
            movies = input.movies.map { movieEntityMapper.map(it) },
            totalPages = input.page,
            totalResults = input.totalResults,
        )
    }
}

class MovieEntityMapper @Inject constructor() : Mapper<MovieModel, MovieEntity> {
    override fun map(input: MovieModel): MovieEntity {
        return MovieEntity(
            id = input.id,
            name = input.name,
            firstAirDate = input.firstAirDate,
            originalTitle = input.originalTitle,
            originalLanguage = input.originalLanguage,
            overview = input.overview,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount
        )
    }

}