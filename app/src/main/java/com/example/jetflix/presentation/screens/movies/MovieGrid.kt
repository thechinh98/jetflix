package com.example.jetflix.presentation.screens.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetflix.LocalNavController
import com.example.jetflix.presentation.common.error.ErrorColumn
import com.example.jetflix.presentation.common.error.ErrorRow
import com.example.jetflix.presentation.common.loading.LoadingColumn
import com.example.jetflix.presentation.screens.movies.movie.MovieContent
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.example.jetflix.R
import com.example.jetflix.data.model.MovieModel
import com.example.jetflix.presentation.common.loading.LoadingRow
import com.example.jetflix.presentation.screens.navigation.Screen
import com.example.jetflix.util.toDp

private const val COLUMN_COUNT = 2
private val GRID_SPACING = 8.dp

private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(COLUMN_COUNT) }

@Composable
fun MoviesGrid(moviesViewModel: MoviesViewModel) {
    val movies = moviesViewModel.movies.collectAsLazyPagingItems()
    val state = rememberLazyGridState()
    LaunchedEffect(Unit) {
        moviesViewModel.filterStateChanges.onEach {
            state.scrollToItem(0)
            movies.refresh()
        }
            .launchIn(this)
    }

    when (movies.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingColumn(stringResource(id = R.string.fetching_movies))
        }
        is LoadState.Error -> {
            val error = movies.loadState.refresh as LoadState.Error
            ErrorColumn(error.error.message.orEmpty())
        }
        else -> {
            LazyMoviesGrid(state, movies)
        }
    }
}

@Composable
private fun LazyMoviesGrid(state: LazyGridState, moviePagingItems: LazyPagingItems<MovieModel>) {
    val navController = LocalNavController.current
    val onMovieClicked: (Int) -> Unit =
        { movieId -> navController.navigate(Screen.DETAIL.createPath(movieId)) }
    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMN_COUNT),
        contentPadding = PaddingValues(
            start = GRID_SPACING,
            end = GRID_SPACING,
            bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current).toDp().dp.plus(
                GRID_SPACING
            )
        ),
        horizontalArrangement = Arrangement.spacedBy(GRID_SPACING, Alignment.CenterHorizontally),
        state = state,
        content = {
            if (moviePagingItems.itemCount == 0 && moviePagingItems.loadState.refresh !is LoadState.Loading) {
                item(span = span) {
                    ErrorRow(stringResource(R.string.no_movies_found))
                }
            }
            items(moviePagingItems.itemCount) { index ->
                val movie = moviePagingItems.peek(index) ?: return@items
                MovieContent(
                    movie,
                    Modifier
                        .height(320.dp)
                        .padding(vertical = GRID_SPACING),
                    onMovieClicked

                )
            }
            renderLoading(moviePagingItems.loadState)
            renderError(moviePagingItems.loadState)
        }
    )
}

private fun LazyGridScope.renderLoading(loadState: CombinedLoadStates) {
    if (loadState.append !is LoadState.Loading) return

    item(span = span) {
        val title = stringResource(R.string.fetching_more_movies)
        LoadingRow(title = title, modifier = Modifier.padding(vertical = GRID_SPACING))
    }
}

private fun LazyGridScope.renderError(loadState: CombinedLoadStates) {
    val message = (loadState.append as? LoadState.Error)?.error?.message ?: return

    item(span = span) {
        ErrorRow(title = message, modifier = Modifier.padding(vertical = GRID_SPACING))
    }
}
