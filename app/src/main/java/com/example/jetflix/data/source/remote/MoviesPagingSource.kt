package com.example.jetflix.data.source.remote

import com.example.jetflix.data.api.MovieApi
import com.example.jetflix.data.mapper.MovieMapper

class MoviesPagingSource(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper,

) {
}