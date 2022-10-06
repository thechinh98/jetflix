package com.example.jetflix.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.jetflix.data.models.LanguageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LanguageDataStore(private val json: Json, private val preferences: DataStore<Preferences>) {

    val language: Flow<LanguageModel> = preferences.data
        .map { preferences ->
            val languageString = preferences[KEY_LANGUAGE]
            if (languageString != null) {
                json.decodeFromString(languageString)
            } else {
                LanguageModel.default
            }
        }

    val languageCode: Flow<String> = language.map { it.iso6391 }

    suspend fun onLanguageSelected(language: LanguageModel) {
        preferences.edit { preferences ->
            preferences[KEY_LANGUAGE] = json.encodeToString(language)
        }
    }

    companion object {
        val KEY_LANGUAGE = stringPreferencesKey("language")
    }
}