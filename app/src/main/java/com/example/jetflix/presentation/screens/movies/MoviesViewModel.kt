package com.example.jetflix.presentation.screens.movies

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetflix.data.model.MovieModel
import com.example.jetflix.data.source.remote.MoviesPagingSource
import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.usecase.filter.GetFilterStateUseCase
import com.example.jetflix.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    getFilterStateUseCase: GetFilterStateUseCase,
    movieUseCase: MovieUseCase
) : ViewModel() {
    val movies: Flow<PagingData<MovieModel>> = movieUseCase.getMovieFlowUseCase.invoke()

    val filterStateChanges = MutableSharedFlow<FilterState>()
    private var filterState: FilterState? = null

    private val searchQuery = MutableStateFlow("")
    private val _searchQueryChanges = MutableSharedFlow<Unit>()
    val searchQueryChanges: SharedFlow<Unit> = _searchQueryChanges.asSharedFlow()

    @VisibleForTesting
    lateinit var pagingSource: MoviesPagingSource
        private set

    init {
        getFilterStateUseCase.invoke()
            .onEach { filterState ->
                this.filterState = filterState
                filterStateChanges.emit(filterState)
            }
            .launchIn(viewModelScope)

        searchQuery
            .debounce(SEARCH_DEBOUNCE_MS)
            .distinctUntilChanged()
            .onEach { _searchQueryChanges.emit(Unit) }
            .launchIn(viewModelScope)
    }

    fun onSearch(query: String) {
        if (searchQuery.value.isEmpty() && query.isBlank()) return
        if (searchQuery.value.isBlank() && query.length < MoviesViewModel.SEARCH_MINIMUM_LENGTH) return

        searchQuery.tryEmit(query)
    }
    companion object {
        private const val SEARCH_DEBOUNCE_MS = 300L
        private const val SEARCH_MINIMUM_LENGTH = 3
    }
}