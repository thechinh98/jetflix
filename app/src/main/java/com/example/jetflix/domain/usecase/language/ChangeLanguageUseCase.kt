package com.example.jetflix.domain.usecase.language

import com.example.jetflix.domain.entities.LanguageEntity
import com.example.jetflix.domain.repository.ConfigurationRepository
import javax.inject.Inject

class ChangeLanguageUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository
) {
    suspend operator fun invoke(language: LanguageEntity) =
        configurationRepository.changeLanguage(language)
}