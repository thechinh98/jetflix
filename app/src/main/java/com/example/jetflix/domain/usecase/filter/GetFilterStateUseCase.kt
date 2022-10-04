package com.example.jetflix.domain.usecase.filter

import com.example.jetflix.data.source.local.FilterDataStore
import javax.inject.Inject

class GetFilterStateUseCase @Inject constructor(private val filterDataStore : FilterDataStore) {
    operator fun invoke() = filterDataStore.filterState
}