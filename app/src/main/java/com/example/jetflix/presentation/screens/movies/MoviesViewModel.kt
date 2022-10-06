package com.example.jetflix.presentation.screens.movies

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.jetflix.data.model.MovieModel
import com.example.jetflix.data.source.remote.MoviesPagingSource
import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.usecase.filter.GetFilterStateUseCase
import com.example.jetflix.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    getFilterStateUseCase: GetFilterStateUseCase,
    movieUseCase: MovieUseCase
) : ViewModel() {
    private var filterState: FilterState? = null
    val movies: Flow<PagingData<MovieModel>> = movieUseCase.getMovieFlowUseCase.invoke(filterState)

    val filterStateChanges = MutableSharedFlow<FilterState>()

    init {
        getFilterStateUseCase.invoke()
            .onEach { filterState ->
                this.filterState = filterState
                filterStateChanges.emit(filterState)
            }
            .launchIn(viewModelScope)

    }
}