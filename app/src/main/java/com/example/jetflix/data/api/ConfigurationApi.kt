package com.example.jetflix.data.api

import com.example.jetflix.data.source.local.Language
import retrofit2.http.GET

interface ConfigurationService {
    @GET("configuration/languages")
    suspend fun fetchLanguages(): List<Language>
}