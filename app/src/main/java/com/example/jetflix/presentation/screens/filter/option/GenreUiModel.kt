package com.example.jetflix.presentation.screens.filter.option

import androidx.compose.ui.graphics.Color
import com.example.jetflix.domain.entities.GenreEntity
import com.example.jetflix.util.randomColor
import kotlinx.serialization.Transient

data class GenreUiModel (
    var id: Int,
    var name: String?
) {
    constructor(genreEntity: GenreEntity) : this(genreEntity.id, genreEntity.name){

    }
    @Transient
    val primaryColor: Color = Color.randomColor()

    @Transient
    val secondaryColor: Color = Color.randomColor()
}