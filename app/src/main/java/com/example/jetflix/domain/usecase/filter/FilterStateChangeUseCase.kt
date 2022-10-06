package com.example.jetflix.domain.usecase.filter

import com.example.jetflix.data.mapper.FilterStateMapper
import com.example.jetflix.data.source.local.FilterDataStore
import com.example.jetflix.domain.entities.FilterStateEntity
import javax.inject.Inject

class FilterStateChangeUseCase @Inject constructor(
    private val filterDataStore: FilterDataStore,
    private val filterStateMapper: FilterStateMapper
) {
    suspend operator fun invoke(filterState: FilterStateEntity) {
        val request = filterStateMapper.reverseMap(filterState)
        if (request != null) {
            filterDataStore.onFilterStateChanged(request)
        }
    }
}