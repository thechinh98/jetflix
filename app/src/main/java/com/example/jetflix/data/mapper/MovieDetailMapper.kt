package com.example.jetflix.data.mapper

import com.example.jetflix.data.models.MovieDetailModel
import com.example.jetflix.domain.entities.GenreEntity
import com.example.jetflix.util.Mapper
import com.example.jetflix.domain.entities.MovieDetailEntities
import com.example.jetflix.domain.entities.ProductionCompanyEntities
import com.example.jetflix.util.toBackdropUrl
import com.example.jetflix.util.toPosterUrl
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : Mapper<MovieDetailModel, MovieDetailEntities> {
    override fun map(input: MovieDetailModel): MovieDetailEntities {
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
            genres = input.genres.map{ GenreEntity(it) },
            releaseDate = input.releaseDate.orEmpty(),
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            duration = input.runtime ?: -1,
            productionCompanies = productionCompanies,
            homepage = input.homepage
        )
    }
}