package com.example.jetflix.domain.usecase.language

import javax.inject.Inject

class ConfigurationUseCase @Inject constructor(
    val fetchLanguageUseCase: FetchLanguageUseCase,
    val getLanguageUseCase: GetLanguageUseCase,
    val changeLanguageUseCase: ChangeLanguageUseCase
) {
}