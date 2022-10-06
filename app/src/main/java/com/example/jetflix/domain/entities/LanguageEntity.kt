package com.example.jetflix.domain.entities

import android.annotation.SuppressLint
import java.util.*

data class LanguageEntity(
    val englishName: String,
    val iso6391: String,
    val name: String
){
    companion object {
        @SuppressLint("ConstantLocale")
        val default = LanguageEntity(
            englishName = Locale.getDefault().displayLanguage,
            iso6391 = Locale.getDefault().language,
            name = Locale.getDefault().displayLanguage
        )
    }
}

inline val LanguageEntity.flagUrl get() = "https://www.unknown.nu/flags/images/$iso6391-100"