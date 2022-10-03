package com.example.jetflix.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val Default = androidx.compose.material.Typography()
val LightTypography = androidx.compose.material.Typography(
    h1 = Default.h1.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 32.sp
    ),
    h2 = Default.h2.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 24.sp
    ),
    h3 = Default.h3.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 20.sp
    ),
    body1 = Default.body1.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 18.sp
    ),
    body2 = Default.body2.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 16.sp
    ),
    subtitle1 = Default.subtitle1.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 14.sp
    ),
    subtitle2 = Default.subtitle2.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 12.sp
    )
)

val DarkTypography = androidx.compose.material.Typography(
    h1 = Default.h1.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 32.sp
    ),
    h2 = Default.h2.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 24.sp
    ),
    h3 = Default.h3.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 20.sp
    ),
    body1 = Default.body1.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 18.sp
    ),
    body2 = Default.body2.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 16.sp
    ),
    subtitle1 = Default.subtitle1.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 14.sp
    ),
    subtitle2 = Default.subtitle2.copy(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 12.sp
    )
)