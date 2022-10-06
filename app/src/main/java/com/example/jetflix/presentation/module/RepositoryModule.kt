package com.example.jetflix.presentation.module

import com.example.jetflix.data.repository.ConfigurationRepositoryImpl
import com.example.jetflix.data.repository.MovieDetailRepositoryImpl
import com.example.jetflix.data.repository.MovieRepositoryImpl
import com.example.jetflix.domain.repository.ConfigurationRepository
import com.example.jetflix.domain.repository.MovieDetailRepository
import com.example.jetflix.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindMovieDetailRepository(repository: MovieDetailRepositoryImpl): MovieDetailRepository

    @Binds
    @Singleton
    abstract fun bindConfigurationRepository(repository: ConfigurationRepositoryImpl): ConfigurationRepository
}