package com.example.jetflix.presentation.screens.movies

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetflix.R
import com.example.jetflix.presentation.common.loading.LoadingColumn
import com.example.jetflix.presentation.screens.filter.FilterBottomSheetContent
import com.example.jetflix.presentation.screens.filter.FilterHeader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesScreen(
    isDarkTheme: MutableState<Boolean>,
    showSettingsDialog: MutableState<Boolean>
) {
    val sheetState = rememberModalBottomSheetState(Hidden)
    val coroutineScope = rememberCoroutineScope()
    val filterViewModel = hiltViewModel<FilterViewModel>()
    val filterState = filterViewModel.filterState.collectAsState().value
    val hideFilterBottomSheet: () -> Unit = {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        scrimColor = Color.DarkGray.copy(alpha = 0.7f),
        sheetContent = {
            val onResetClicked = if (filterState == null) null else filterViewModel::onResetClicked
            FilterHeader(onHideClicked = hideFilterBottomSheet, onResetClicked = onResetClicked)

            if (filterState == null) {
                LoadingColumn(
                    title = stringResource(id = R.string.loading_filter_options),
                    modifier = Modifier.fillMaxHeight(0.4f)
                )
            } else {
                FilterBottomSheetContent(
                    filterState = filterState,
                    onFilterStateChanged = filterViewModel::onFilterStateChanged
                )
            }
        },
        content = {
            MoviesGrid(isDarkTheme, showSettingsDialog, sheetState)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MoviesGrid(
    isDarkTheme: MutableState<Boolean>,
    showSettingsDialog: MutableState<Boolean>,
    bottomSheetState: ModalBottomSheetState
) {
    val moviesViewModel = hiltViewModel<MoviesViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val searchQuery = remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            Surface(modifier = Modifier.fillMaxWidth(), elevation = 16.dp) {
                Column(
                    Modifier
                        .background(MaterialTheme.colors.surface)
                        .padding(bottom = 2.dp)
                ) {
                    JetflixAppBar(isDarkTheme, showSettingsDialog)
//                    SearchBar(searchQuery, moviesViewModel::onSearch)
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(visible = searchQuery.value.isBlank()) {
                FloatingActionButton(
                    modifier = Modifier
                        .wrapContentSize()
                        .navigationBarsPadding(),
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }
                    },
                    content = {
                        val color =
                            if (isDarkTheme.value) MaterialTheme.colors.surface else MaterialTheme.colors.onPrimary
                        val tint = animateColorAsState(color).value
                        Image(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = stringResource(id = R.string.title_filter_bottom_sheet),
                            colorFilter = ColorFilter.tint(tint)
                        )
                    }
                )
            }
        },
        content = {
            MoviesGrid(moviesViewModel)
        }
    )
}

@Composable
private fun JetflixAppBar(
    isDarkTheme: MutableState<Boolean>,
    showSettingsDialog: MutableState<Boolean>
) {
    val colors = MaterialTheme.colors
    val tint =
        animateColorAsState(if (isDarkTheme.value) colors.onSurface else colors.primary).value
    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { showSettingsDialog.value = true }) {
            Icon(
                Icons.Default.Settings,
                contentDescription = stringResource(id = R.string.settings_content_description),
                tint = tint
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_jetflix),
            contentDescription = stringResource(id = R.string.app_name),
            tint = tint,
            modifier = Modifier.size(82.dp)
        )

        val icon = if (isDarkTheme.value) Icons.Default.NightsStay else Icons.Default.WbSunny
        IconButton(onClick = { isDarkTheme.value = isDarkTheme.value.not() }) {
            val contentDescriptionResId = if (isDarkTheme.value) {
                R.string.light_theme_content_description
            } else {
                R.string.dark_theme_content_description
            }
            Icon(
                icon,
                contentDescription = stringResource(id = contentDescriptionResId),
                tint = tint
            )
        }
    }
}
