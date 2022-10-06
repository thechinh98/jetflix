package com.example.jetflix.data.mapper

import com.example.jetflix.data.models.CreditsModel
import com.example.jetflix.util.Mapper
import com.example.jetflix.domain.entities.CreditsEntities
import com.example.jetflix.domain.entities.GenderEntity
import com.example.jetflix.domain.entities.PersonEntities
import com.example.jetflix.util.toProfilePhotoUrl
import javax.inject.Inject

class CreditsMapper @Inject constructor() : Mapper<CreditsModel, CreditsEntities> {
    override fun map(input: CreditsModel): CreditsEntities {
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
