package com.example.jetflix.data.repository

import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetflix.data.api.MovieApi
import com.example.jetflix.data.mapper.MovieMapper
import com.example.jetflix.data.mapper.MovieRequestOptionsMapper
import com.example.jetflix.data.model.MovieModel
import com.example.jetflix.data.source.remote.MovieRemote
import com.example.jetflix.data.source.remote.MoviesPagingSource
import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.entities.MoviesResponse
import com.example.jetflix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemote: MovieRemote,
    private val movieMapper: MovieMapper,

    private val filterState: FilterState,
) : MovieRepository {
    override suspend fun fetchGenre() = movieRemote.fetchGenre()

    override suspend fun fetchMovies(pageNumber: Int, filterState: FilterState?): MoviesResponse {
        return movieRemote.fetchMovies(pageNumber, filterState)
    }

    override suspend fun getPagingSource(): Flow<PagingData<MovieModel>> {
        @VisibleForTesting
        fun initPagingSource() = MoviesPagingSource(
            movieMapper = movieMapper,
            filterState = filterState,
            movieRemote = movieRemote,
        )

        val pager: Pager<Int, MovieModel> =
            Pager(config = PagingConfig(pageSize = 20), pagingSourceFactory = ::initPagingSource)

        return pager.flow
    }


}