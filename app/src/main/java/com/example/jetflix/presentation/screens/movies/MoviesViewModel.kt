package com.example.jetflix.presentation.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.jetflix.data.model.MovieModel
import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.usecase.filter.GetFilterStateUseCase
import com.example.jetflix.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    getFilterStateUseCase: GetFilterStateUseCase,
    movieUseCase: MovieUseCase
) : ViewModel() {
    private var filterState: FilterState? = null
    val movies: Flow<PagingData<MovieModel>> = movieUseCase.getMovieFlowUseCase.flow

    val filterStateChanges = MutableSharedFlow<FilterState>()

    init {
        getFilterStateUseCase.invoke()
            .onEach { filterState ->
                this.filterState = filterState
                movieUseCase.getMovieFlowUseCase.invoke(filterState)
                filterStateChanges.emit(filterState)
            }
            .launchIn(viewModelScope)

    }
}