package com.example.jetflix

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetflix.presentation.screens.movies.MoviesScreen
import com.example.jetflix.presentation.screens.navigation.ARG_MOVIE_ID
import com.example.jetflix.presentation.screens.navigation.Screen

val LocalNavController = compositionLocalOf<NavHostController> { error("No nav controller") }
@Composable
fun MainContent(
    isDarkTheme: MutableState<Boolean>,
    showSettingsDialog: MutableState<Boolean>
) {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = Screen.MOVIES.route) {
        composable(Screen.MOVIES.route) { MoviesScreen(isDarkTheme, showSettingsDialog) }

        navigation(startDestination = Screen.DETAIL.route, route = "movie") {
            argument(ARG_MOVIE_ID) { type = NavType.StringType }

            fun NavBackStackEntry.movieId(): Int {
                return arguments?.getString(ARG_MOVIE_ID)!!.toInt()
            }

//            val movieDetailViewModel: @Composable (movieId: Int) -> MovieDetailViewModel = { hiltViewModel() }

//            composable(route = Screen.DETAIL.route) {
//                MovieDetailScreen(movieDetailViewModel(it.movieId()))
//            }
//
//            composable(
//                route = Screen.IMAGES.route,
//                arguments = listOf(navArgument(ARG_INITIAL_PAGE) { defaultValue = "0" })
//            ) {
//                val initialPage = it.arguments?.getString(ARG_INITIAL_PAGE)!!.toInt()
//                val images = movieDetailViewModel(it.movieId()).uiState.collectAsState().value.images
//                ImagesScreen(images, initialPage)
//            }
//
//            composable(route = Screen.CAST.route) {
//                val cast = movieDetailViewModel(it.movieId()).uiState.collectAsState().value.credits.cast
//                PeopleGridScreen(cast)
//            }
//
//            composable(route = Screen.CREW.route) {
//                val crew = movieDetailViewModel(it.movieId()).uiState.collectAsState().value.credits.crew
//                PeopleGridScreen(crew)
//            }
        }
    }

//    if (showSettingsDialog.value) {
//        SettingsContent {
//            showSettingsDialog.value = false
//        }
//    }
}
