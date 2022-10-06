package com.example.jetflix.data.models

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class LanguageModel(
    @SerialName("english_name") val englishName: String,
    @SerialName("iso_639_1") val iso6391: String,
    @SerialName("name") val name: String
){
    companion object {
        @SuppressLint("ConstantLocale")
        val default = LanguageModel(
            englishName = Locale.getDefault().displayLanguage,
            iso6391 = Locale.getDefault().language,
            name = Locale.getDefault().displayLanguage
        )
    }
}
