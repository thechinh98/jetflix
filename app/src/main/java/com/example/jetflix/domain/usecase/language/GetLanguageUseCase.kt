package com.example.jetflix.domain.usecase.language

import com.example.jetflix.domain.repository.ConfigurationRepository
import javax.inject.Inject

class GetLanguageUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository
) {
    operator fun invoke() = configurationRepository.getLanguage()
}