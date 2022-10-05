package com.example.jetflix.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.material.icons.rounded.Woman

data class Credits(val cast: List<PersonUiModel>, val crew: List<PersonUiModel>)

data class PersonUiModel(
    val name: String,
    val role: String,
    val profilePhotoUrl: String?,
    val gender: Gender
)

enum class Gender { MALE, FEMALE }

val Gender.placeholderIcon
    get() = when (this) {
        Gender.MALE -> Icons.Rounded.Man
        Gender.FEMALE -> Icons.Rounded.Woman
    }
