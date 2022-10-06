package com.example.jetflix.domain.usecase.filter

import android.util.Log
import com.example.jetflix.data.source.local.FilterDataStore
import com.example.jetflix.domain.entities.FilterState
import javax.inject.Inject

class FilterStateChangeUseCase @Inject constructor(private val filterDataStore: FilterDataStore) {
    suspend operator fun invoke(filterState: FilterState) {
        filterDataStore.onFilterStateChanged(filterState)
    }
}