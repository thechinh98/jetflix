package com.example.jetflix.data.api

import com.example.jetflix.domain.entities.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieApi {
    @GET("discover/movie")
    suspend fun fetchMovies(
        @Query("page") pageNumber: Int,
        @QueryMap options: Map<String, String>
    ): MoviesResponse

    @GET("search/movie")
    suspend fun search(
        @Query("page") pageNumber: Int,
        @Query("query") searchQuery: String,
        @Query("include_adult") includeAdult: Boolean = true
    ): MoviesResponse

    @GET("genre/movie/list")
    suspend fun fetchGenres(): GenresResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetail(@Path("movie_id") movieId: Int): MovieDetailEntity

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(@Path("movie_id") movieId: Int): CreditsEntity

    @GET("movie/{movie_id}/images")
    suspend fun fetchMovieImages(@Path("movie_id") movieId: Int): ImagesEntity

    @GET("configuration/languages")
    suspend fun fetchLanguages(): List<Language>
}