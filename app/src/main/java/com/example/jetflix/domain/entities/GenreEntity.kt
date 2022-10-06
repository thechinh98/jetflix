package com.example.jetflix.domain.entities

import androidx.compose.ui.graphics.Color
import com.example.jetflix.data.models.GenreModel
import com.example.jetflix.util.randomColor
import kotlinx.serialization.Transient

data class GenreEntity (
    var id: Int,
    var name: String?
) {
    constructor(genreEntity: GenreModel) : this(genreEntity.id, genreEntity.name){

    }
    @Transient
    val primaryColor: Color = Color.randomColor()

    @Transient
    val secondaryColor: Color = Color.randomColor()
}