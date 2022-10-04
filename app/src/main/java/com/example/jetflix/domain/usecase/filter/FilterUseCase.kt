package com.example.jetflix.domain.usecase.filter

import javax.inject.Inject

data class FilterUseCase @Inject constructor(
    val filterStateChangeUseCase: FilterStateChangeUseCase,
    val getFilterStateUseCase: GetFilterStateUseCase,
    val resetFilterStateUseCase: ResetFilterStateUseCase
){
}