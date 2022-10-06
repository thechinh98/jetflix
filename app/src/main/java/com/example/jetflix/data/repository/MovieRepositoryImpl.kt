package com.example.jetflix.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetflix.data.mapper.GenreMapper
import com.example.jetflix.data.mapper.MovieMapper
import com.example.jetflix.data.mapper.MoviesResponseMapper
import com.example.jetflix.data.models.FilterState
import com.example.jetflix.data.source.remote.MovieRemote
import com.example.jetflix.data.source.remote.MoviesPagingSource
import com.example.jetflix.domain.entities.GenreEntity
import com.example.jetflix.domain.entities.MovieEntities
import com.example.jetflix.domain.entities.MoviesResponseEntities
import com.example.jetflix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemote: MovieRemote,
    private val movieResponseMapper: MoviesResponseMapper,
    private val movieMapper: MovieMapper,
    private val genreMapper: GenreMapper
) : MovieRepository {

    override suspend fun fetchGenre(): List<GenreEntity> {
        val result = movieRemote.fetchGenre()
        return result.genreModels.map { genreMapper.map(it) }
    }

    override suspend fun fetchMovies(
        pageNumber: Int,
        filterState: FilterState?
    ): MoviesResponseEntities {
        val result = movieRemote.fetchMovies(pageNumber, filterState)
        return movieResponseMapper.map(result)
    }

    override fun getPagingSource(filterState: FilterState?): Flow<PagingData<MovieEntities>> {
        val pager: Pager<Int, MovieEntities> = Pager(
            config = PagingConfig(pageSize = 20)
        ) {
            MoviesPagingSource(
                movieMapper = movieMapper,
                filterState = filterState,
                movieRemote = movieRemote,
            )
        }

        return pager.flow
    }


}