package com.example.jetflix.presentation.mapper

import com.example.jetflix.data.model.Credits
import com.example.jetflix.data.model.Gender
import com.example.jetflix.data.model.PersonUiModel
import com.example.jetflix.domain.entities.CreditsEntity
import com.example.jetflix.util.Mapper
import com.example.jetflix.util.toProfilePhotoUrl
import javax.inject.Inject

class CreditsMapper @Inject constructor() : Mapper<CreditsEntity, Credits> {
    override fun map(input: CreditsEntity): Credits {
        val cast = input.cast.map { cast ->
            PersonUiModel(cast.name, cast.character, cast.profilePath?.toProfilePhotoUrl(), cast.gender.toGender())
        }
        val crew = input.crew.map { crew ->
            PersonUiModel(crew.name, crew.job, crew.profilePath?.toProfilePhotoUrl(), crew.gender.toGender())
        }
        return Credits(cast, crew)
    }

    private fun Int.toGender() = if (this == 1) Gender.FEMALE else Gender.MALE
}
