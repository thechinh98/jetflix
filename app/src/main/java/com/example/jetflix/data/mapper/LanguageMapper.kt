package com.example.jetflix.data.mapper

import com.example.jetflix.data.models.LanguageModel
import com.example.jetflix.domain.entities.LanguageEntity
import com.example.jetflix.util.Mapper
import javax.inject.Inject

class LanguageMapper @Inject constructor() : Mapper<LanguageModel, LanguageEntity> {
    override fun map(input: LanguageModel): LanguageEntity {
        return LanguageEntity(
            name = input.name,
            iso6391 = input.iso6391,
            englishName = input.englishName
        )
    }

    fun reverseMap(input: LanguageEntity): LanguageModel {
        return LanguageModel(
            name = input.name,
            iso6391 = input.iso6391,
            englishName = input.englishName
        )
    }

}