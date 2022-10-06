package com.example.jetflix.domain.usecase.movie

import androidx.paging.PagingData
import com.example.jetflix.data.mapper.FilterStateMapper
import com.example.jetflix.domain.entities.MovieEntities
import com.example.jetflix.data.models.FilterState
import com.example.jetflix.domain.entities.FilterStateEntity
import com.example.jetflix.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetMovieFlowUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val filterStateMapper: FilterStateMapper
) {
    private val paramState = MutableSharedFlow<FilterState?>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val flow: Flow<PagingData<MovieEntities>> = paramState
        .distinctUntilChanged()
        .flatMapLatest { createObservable(it) }
        .distinctUntilChanged()

    private fun createObservable(filterState: FilterState?) =
        movieRepository.getPagingSource(filterState)

    operator fun invoke(filterState: FilterStateEntity?) {
        val request = filterStateMapper.reverseMap(filterState)
        paramState.tryEmit(request)
    }
}