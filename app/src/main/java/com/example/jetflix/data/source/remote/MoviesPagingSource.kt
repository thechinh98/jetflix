package com.example.jetflix.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetflix.data.mapper.MovieMapper
import com.example.jetflix.data.model.MovieModel
import com.example.jetflix.domain.entities.FilterState
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val movieMapper: MovieMapper,
    val filterState: FilterState? = null,
    val movieRemote: MovieRemote,
) : PagingSource<Int, MovieModel>() {


    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
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