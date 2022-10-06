package com.example.jetflix.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.material.icons.rounded.Woman

data class CreditsEntities(val cast: List<PersonEntities>, val crew: List<PersonEntities>)

data class PersonEntities(
    val name: String,
    val role: String,
    val profilePhotoUrl: String?,
    val gender: GenderEntity
)

enum class GenderEntity { MALE, FEMALE }

val GenderEntity.placeholderIcon
    get() = when (this) {
        GenderEntity.MALE -> Icons.Rounded.Man
        GenderEntity.FEMALE -> Icons.Rounded.Woman
    }
