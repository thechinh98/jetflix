package com.example.jetflix.domain.usecase.filter

import android.util.Log
import com.example.jetflix.data.source.local.FilterDataStore
import com.example.jetflix.domain.entities.FilterState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilterStateUseCase @Inject constructor(private val filterDataStore : FilterDataStore) {
    operator fun invoke(): Flow<FilterState> {
        Log.d("Chinhlt", "GetFilterStateUseCase dm chinh: ${filterDataStore.filterState}")
        return filterDataStore.filterState
    }
}