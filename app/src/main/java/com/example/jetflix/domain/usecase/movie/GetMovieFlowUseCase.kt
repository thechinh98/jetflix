package com.example.jetflix.domain.usecase.movie

import androidx.paging.PagingData
import com.example.jetflix.data.model.MovieModel
import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetMovieFlowUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    private val paramState = MutableSharedFlow<FilterState?>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    @OptIn(ExperimentalCoroutinesApi::class)
    val flow: Flow<PagingData<MovieModel>> = paramState
        .distinctUntilChanged()
        .flatMapLatest { createObservable(it) }
        .distinctUntilChanged()

    private fun createObservable(filterState: FilterState?) =
        movieRepository.getPagingSource(filterState)

    operator fun invoke(filterState: FilterState?) {
        paramState.tryEmit(filterState)
    }
}