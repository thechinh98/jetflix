package com.example.jetflix.domain.usecase.language

import com.example.jetflix.domain.repository.ConfigurationRepository
import javax.inject.Inject

class FetchLanguageUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository
) {
    suspend operator fun invoke() = configurationRepository.fetchConfiguration()
}