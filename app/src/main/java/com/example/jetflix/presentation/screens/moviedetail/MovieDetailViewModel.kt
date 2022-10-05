package com.example.jetflix.presentation.screens.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetflix.data.model.Credits
import com.example.jetflix.data.model.Image
import com.example.jetflix.data.model.MovieDetail
import com.example.jetflix.domain.usecase.moviedetail.MovieDetailUseCase
import com.example.jetflix.presentation.mapper.CreditsMapper
import com.example.jetflix.presentation.mapper.ImageMapper
import com.example.jetflix.presentation.mapper.MovieDetailMapper
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
    private val movieDetailMapper: MovieDetailMapper,
    private val creditsMapper: CreditsMapper,
    private val imageMapper: ImageMapper,
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
                    movieDetail = movieDetailMapper.map(movieDetailResponse.await()),
                    credits = creditsMapper.map(creditsResponse.await()),
                    images = imageMapper.map(imagesResponse.await()),
                    loading = false
                )
            }
        } catch (exception: Exception) {
            _uiState.value.copy(error = exception, loading = false)
        }
    }

    data class MovieDetailUiState(
        val movieDetail: MovieDetail? = null,
        val credits: Credits = Credits(listOf(), listOf()),
        val images: List<Image> = listOf(),
        val loading: Boolean = false,
        val error: Throwable? = null
    )
}
