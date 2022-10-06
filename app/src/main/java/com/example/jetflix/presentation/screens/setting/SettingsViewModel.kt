package com.example.jetflix.presentation.screens.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetflix.domain.entities.LanguageEntity
import com.example.jetflix.domain.usecase.language.ConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val configurationUseCase: ConfigurationUseCase
) : ViewModel() {

    val selectedLanguage = configurationUseCase.getLanguageUseCase.invoke()
    val onSettingsChanged = MutableLiveData<Unit>()
    val uiState = MutableStateFlow(UiState())

    var uiValue
        get() = uiState.value
        set(value) {
            uiState.value = value
        }

    fun fetchLanguages() {
        val canFetchLanguages = uiValue.languages.isEmpty() && uiValue.showLoading.not()
        if (canFetchLanguages) {
            viewModelScope.launch {
                uiValue = uiValue.copy(showLoading = true)
                uiValue = try {
                    val languages =
                        configurationUseCase.fetchLanguageUseCase.invoke()
                            .sortedBy(LanguageEntity::englishName)
                    uiValue.copy(showLoading = false, languages = languages)
                } catch (exception: Exception) {
                    uiValue.copy(showLoading = false)
                }
            }
        }
    }

    fun onLanguageSelected(language: LanguageEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            configurationUseCase.changeLanguageUseCase.invoke(language)
        }
        onSettingsChanged.value = Unit
    }

    data class UiState(
        val showLoading: Boolean = false,
        val languages: List<LanguageEntity> = emptyList()
    )
}
