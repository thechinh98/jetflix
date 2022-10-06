package com.example.jetflix.presentation.screens.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetflix.domain.entities.CreditsEntities
import com.example.jetflix.domain.entities.ImageEntities
import com.example.jetflix.domain.entities.MovieDetailEntities
import com.example.jetflix.domain.usecase.moviedetail.MovieDetailUseCase
import com.example.jetflix.presentation.screens.navigation.ARG_MOVIE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieDetailUseCase: MovieDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    init {
        val movieId: Int = savedStateHandle.get<String>(ARG_MOVIE_ID)!!.toInt()
        fetchMovieDetail(movieId = movieId)
    }

    private fun fetchMovieDetail(movieId: Int) = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(loading = true, error = null)
        _uiState.value = try {
            coroutineScope {
                val movieDetailResponse = async { movieDetailUseCase.fetchMovieDetailUseCase(movieId) }
                val creditsResponse = async { movieDetailUseCase.fetchMovieCreditUseCase(movieId) }
                val imagesResponse = async { movieDetailUseCase.fetchMovieImage(movieId)}
                _uiState.value.copy(
                    movieDetail = movieDetailResponse.await(),
                    credits = creditsResponse.await(),
                    images = imagesResponse.await(),
                    loading = false
                )
            }
        } catch (exception: Exception) {
            _uiState.value.copy(error = exception, loading = false)
        }
    }

    data class MovieDetailUiState(
        val movieDetail: MovieDetailEntities? = null,
        val credits: CreditsEntities = CreditsEntities(listOf(), listOf()),
        val images: List<ImageEntities> = listOf(),
        val loading: Boolean = false,
        val error: Throwable? = null
    )
}
