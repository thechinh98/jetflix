package com.example.jetflix.presentation.mapper

import com.example.jetflix.data.model.MovieDetailEntities
import com.example.jetflix.data.model.ProductionCompanyEntities
import com.example.jetflix.domain.entities.MovieDetailEntity
import com.example.jetflix.presentation.screens.filter.option.GenreUiModel
import com.example.jetflix.util.Mapper
import com.example.jetflix.util.toBackdropUrl
import com.example.jetflix.util.toPosterUrl
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : Mapper<MovieDetailEntity, MovieDetailEntities> {
    override fun map(input: MovieDetailEntity): MovieDetailEntities {
        val productionCompanies = input.productionCompanies.map {
            ProductionCompanyEntities(it.name, it.logoPath.orEmpty().toPosterUrl())
        }
        return MovieDetailEntities(
            id = input.id,
            title = input.title,
            originalTitle = input.originalTitle,
            overview = input.overview,
            tagline = input.tagline.dropLastWhile { it == '.' },
            backdropUrl = input.backdropPath.orEmpty().toBackdropUrl(),
            posterUrl = input.posterPath.toPosterUrl(),
            genres = input.genres.map{ GenreUiModel(it) },
            releaseDate = input.releaseDate.orEmpty(),
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            duration = input.runtime ?: -1,
            productionCompanies = productionCompanies,
            homepage = input.homepage
        )
    }
}