package com.example.jetflix.data.model

import androidx.compose.ui.graphics.Color
import com.example.jetflix.domain.entities.GenreEntity
import com.example.jetflix.util.randomColor
import kotlinx.serialization.Transient

class GenreUiModel {
    val genreEntities: GenreEntity = GenreEntity(-1, "")

    @Transient
    val primaryColor: Color = Color.randomColor()

    @Transient
    val secondaryColor: Color = Color.randomColor()
}