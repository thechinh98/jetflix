package com.example.jetflix.domain.repository

import com.example.jetflix.domain.entities.LanguageEntity
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    suspend fun fetchConfiguration(): List<LanguageEntity>
    fun getLanguage(): Flow<LanguageEntity>
    suspend fun changeLanguage(language: LanguageEntity)
}