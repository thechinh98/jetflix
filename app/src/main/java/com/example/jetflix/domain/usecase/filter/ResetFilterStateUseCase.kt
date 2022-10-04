package com.example.jetflix.domain.usecase.filter

import com.example.jetflix.data.source.local.FilterDataStore
import javax.inject.Inject

class ResetFilterStateUseCase @Inject constructor(private val filterDataStore: FilterDataStore) {
    suspend operator fun invoke() = filterDataStore.resetFilterState()
}