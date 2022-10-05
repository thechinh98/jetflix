package com.example.jetflix.presentation.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.usecase.genre.FetchGenreUseCase
import com.example.jetflix.domain.usecase.filter.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase,
    private val fetchGenreUseCase: FetchGenreUseCase
) : ViewModel() {
    private val _filterState: MutableStateFlow<FilterState?> = MutableStateFlow(null)
    val filterState: StateFlow<FilterState?> = _filterState.also {
        listenFilterStateChanges()
    }

    private fun listenFilterStateChanges() = viewModelScope.launch {
        val genres = try {
            val genre = fetchGenreUseCase.invoke()
            genre.genreEntities
        } catch (exception: Exception) {
            emptyList()
        }

        filterUseCase.getFilterStateUseCase.invoke()
            .map { filterState -> filterState.copy(genres = genres) }
            .collect(_filterState::value::set)
    }

    fun onResetClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            filterUseCase.resetFilterStateUseCase.invoke()
        }
    }

    fun onFilterStateChanged(filterState: FilterState) {
        viewModelScope.launch(Dispatchers.IO) {
            filterUseCase.filterStateChangeUseCase.invoke(filterState)
        }
    }
}