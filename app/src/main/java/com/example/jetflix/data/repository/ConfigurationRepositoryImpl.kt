package com.example.jetflix.data.repository

import com.example.jetflix.data.source.local.LanguageDataStore
import com.example.jetflix.data.source.remote.ConfigurationRemote
import com.example.jetflix.domain.entities.Language
import com.example.jetflix.domain.repository.ConfigurationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationRemote: ConfigurationRemote,
    private val languageDataStore: LanguageDataStore
) : ConfigurationRepository {
    override suspend fun fetchConfiguration(): List<Language> {
        return configurationRemote.fetchLanguage()
    }

    override fun getLanguage(): Flow<Language> {
        return languageDataStore.language
    }

    override suspend fun changeLanguage(language: Language) {
        languageDataStore.onLanguageSelected(language)
    }
}