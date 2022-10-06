package com.example.jetflix.data.mapper

import com.example.jetflix.data.models.GenreModel
import com.example.jetflix.domain.entities.GenreEntity
import com.example.jetflix.util.Mapper
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreModel, GenreEntity> {
    override fun map(input: GenreModel): GenreEntity {
        return GenreEntity(
            id = input.id,
            name = input.name
        )
    }
    fun reverseMap(input: GenreEntity): GenreModel {
        return GenreModel(
            id = input.id,
            name = input.name
        )
    }
}