package com.example.jetflix.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.jetflix.data.models.FilterState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FilterDataStore @Inject constructor(
    private val json: Json,
    private val preferences: DataStore<Preferences>
) {
    val filterState: Flow<FilterState> = preferences.data
        .map { preferences ->
            val filterStateString = preferences[KEY_FILTER_STATE]
            if (filterStateString != null) {
                json.decodeFromString(filterStateString)
            } else {
                FilterState()
            }
        }
        .catch {
            emit(FilterState())
        }

    suspend fun onFilterStateChanged(filterState: FilterState) {
        preferences.edit { preferences ->
            preferences[KEY_FILTER_STATE] = json.encodeToString(filterState)
        }
    }

    suspend fun resetFilterState() {
        preferences.edit { preferences ->
            preferences[KEY_FILTER_STATE] = json.encodeToString(FilterState())
        }
    }

    companion object {
        val KEY_FILTER_STATE = stringPreferencesKey("filter_state")
    }
}
