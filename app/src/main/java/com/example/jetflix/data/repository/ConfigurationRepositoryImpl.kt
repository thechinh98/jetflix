package com.example.jetflix.data.repository

import com.example.jetflix.data.mapper.LanguageMapper
import com.example.jetflix.data.source.local.LanguageDataStore
import com.example.jetflix.data.source.remote.ConfigurationRemote
import com.example.jetflix.domain.entities.LanguageEntity
import com.example.jetflix.domain.repository.ConfigurationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationRemote: ConfigurationRemote,
    private val languageDataStore: LanguageDataStore,
    private val languageMapper: LanguageMapper,
) : ConfigurationRepository {
    override suspend fun fetchConfiguration(): List<LanguageEntity> {
        val result = configurationRemote.fetchLanguage()
        return result.map { languageMapper.map(it) }
    }

    override fun getLanguage(): Flow<LanguageEntity> {
        val resultFlow = languageDataStore.language

        return resultFlow.map { languageMapper.map(it) }
    }

    override suspend fun changeLanguage(language: LanguageEntity) {
        val request = languageMapper.reverseMap(language)
        languageDataStore.onLanguageSelected(request)
    }
}