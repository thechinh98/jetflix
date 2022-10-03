package com.example.jetflix.presentation.screens.movies

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesScreen() {
    val sheetState = rememberModalBottomSheetState(Hidden)
    val coroutineScope = rememberCoroutineScope()
    val hideFilterBottomSheet: () -> Unit = {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

}