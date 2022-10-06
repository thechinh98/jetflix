package com.example.jetflix.presentation.mapper

import com.example.jetflix.data.model.CreditsEntities
import com.example.jetflix.data.model.GenderEntity
import com.example.jetflix.data.model.PersonEntities
import com.example.jetflix.domain.entities.CreditsEntity
import com.example.jetflix.util.Mapper
import com.example.jetflix.util.toProfilePhotoUrl
import javax.inject.Inject

class CreditsMapper @Inject constructor() : Mapper<CreditsEntity, CreditsEntities> {
    override fun map(input: CreditsEntity): CreditsEntities {
        val cast = input.cast.map { cast ->
            PersonEntities(cast.name, cast.character, cast.profilePath?.toProfilePhotoUrl(), cast.gender.toGender())
        }
        val crew = input.crew.map { crew ->
            PersonEntities(crew.name, crew.job, crew.profilePath?.toProfilePhotoUrl(), crew.gender.toGender())
        }
        return CreditsEntities(cast, crew)
    }

    private fun Int.toGender() = if (this == 1) GenderEntity.FEMALE else GenderEntity.MALE
}
