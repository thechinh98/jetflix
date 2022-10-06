package com.example.jetflix.presentation.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetflix.domain.entities.FilterStateEntity
import com.example.jetflix.domain.entities.GenreEntity
import com.example.jetflix.domain.usecase.filter.FilterUseCase
import com.example.jetflix.domain.usecase.genre.FetchGenreUseCase
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
    private val _filterState: MutableStateFlow<FilterStateEntity?> = MutableStateFlow(null)
    val filterState: StateFlow<FilterStateEntity?> = _filterState.also {
        listenFilterStateChanges()
    }

    private fun listenFilterStateChanges() = viewModelScope.launch {
        val genres : List<GenreEntity> = try {
            fetchGenreUseCase.invoke()
        } catch (exception: Exception) {
            emptyList<GenreEntity>()
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

    fun onFilterStateChanged(filterState: FilterStateEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            filterUseCase.filterStateChangeUseCase.invoke(filterState)
        }
    }
}