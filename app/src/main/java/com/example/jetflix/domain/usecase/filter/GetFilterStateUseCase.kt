package com.example.jetflix.domain.usecase.filter

import com.example.jetflix.data.mapper.FilterStateMapper
import com.example.jetflix.data.source.local.FilterDataStore
import com.example.jetflix.domain.entities.FilterStateEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFilterStateUseCase @Inject constructor(
    private val filterDataStore: FilterDataStore,
    private val filterStateMapper: FilterStateMapper
) {
    operator fun invoke(): Flow<FilterStateEntity> {
        return filterDataStore.filterState.map { filterStateMapper.map(it) }
    }
}