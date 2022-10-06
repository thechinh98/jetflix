package com.example.jetflix.presentation.screens.filter.option

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.example.jetflix.R
import com.example.jetflix.data.models.SortOrderEnum
import com.example.jetflix.domain.entities.FilterStateEntity
import com.example.jetflix.presentation.screens.filter.FilterGrid
import com.example.jetflix.presentation.screens.filter.FilterRadioItem
import com.example.jetflix.presentation.screens.filter.FilterSectionDivider
import com.example.jetflix.presentation.screens.filter.FilterSectionTitle

data class SortOrderOption(override val defaultValue: SortOrderEnum) : FilterOption<SortOrderEnum> {
    override var currentValue: SortOrderEnum = defaultValue

    override fun modifyFilterState(filterState: FilterStateEntity) =
        filterState.copy(sortOrder = currentValue)

    @Composable
    override fun Render(onChanged: () -> Unit) {
        val sortOrderState = remember(defaultValue) { mutableStateOf(currentValue) }
        FilterSectionTitle(
            painter = rememberVectorPainter(image = Icons.Default.SwapVert),
            title = R.string.sort_order
        )
        val sortOrderValues = SortOrderEnum.values().toList()
        FilterGrid(items = sortOrderValues) { index, _ ->
            val sortOrder = sortOrderValues[index]
            val selected = sortOrderState.value == sortOrder
            FilterRadioItem(
                title = stringResource(id = sortOrder.titleResId),
                selected = selected
            ) {
                currentValue = sortOrder
                sortOrderState.value = currentValue
                onChanged()
            }
        }
        FilterSectionDivider()
    }
}