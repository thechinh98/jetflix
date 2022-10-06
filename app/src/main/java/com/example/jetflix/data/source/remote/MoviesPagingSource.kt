package com.example.jetflix.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetflix.data.mapper.MovieMapper
import com.example.jetflix.domain.entities.MovieEntities
import com.example.jetflix.data.models.FilterState

class MoviesPagingSource(
    private val movieMapper: MovieMapper,
    val filterState: FilterState? = null,
    val movieRemote: MovieRemote,
) : PagingSource<Int, MovieEntities>() {


    override fun getRefreshKey(state: PagingState<Int, MovieEntities>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntities> {
        return try {
            val page = params.key ?: 1
            val moviesResponse = movieRemote.fetchMovies(page, filterState)
            val movies = moviesResponse.movies.map(movieMapper::map)
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= moviesResponse.totalPages) null else moviesResponse.page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}