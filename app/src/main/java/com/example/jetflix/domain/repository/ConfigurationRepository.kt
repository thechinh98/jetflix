package com.example.jetflix.domain.repository

import com.example.jetflix.domain.entities.Language
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    suspend fun fetchConfiguration(): List<Language>
    fun getLanguage(): Flow<Language>
    suspend fun changeLanguage(language: Language)
}