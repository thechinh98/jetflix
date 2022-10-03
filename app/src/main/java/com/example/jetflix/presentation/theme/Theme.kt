package com.example.jetflix.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun JetflixTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (isDarkTheme) DarkThemeColors else LightThemeColors
    val typography = if (isDarkTheme) DarkTypography else LightTypography
    MaterialTheme(colors = colors, typography = typography, content = content)
}