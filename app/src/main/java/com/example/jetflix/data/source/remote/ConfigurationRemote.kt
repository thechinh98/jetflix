package com.example.jetflix.data.source.remote

import com.example.jetflix.data.api.MovieApi
import com.example.jetflix.data.models.LanguageModel
import javax.inject.Inject

class ConfigurationRemote @Inject constructor(
    private val configurationApi: MovieApi
) {
    suspend fun fetchLanguage() : List<LanguageModel> = configurationApi.fetchLanguages()
}