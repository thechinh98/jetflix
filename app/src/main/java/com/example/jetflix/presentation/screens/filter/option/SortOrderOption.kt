package com.example.jetflix.presentation.screens.filter.option

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.example.jetflix.R
import com.example.jetflix.domain.entities.FilterState
import com.example.jetflix.domain.entities.SortOrder
import com.example.jetflix.presentation.screens.filter.FilterGrid
import com.example.jetflix.presentation.screens.filter.FilterRadioItem
import com.example.jetflix.presentation.screens.filter.FilterSectionDivider
import com.example.jetflix.presentation.screens.filter.FilterSectionTitle

data class SortOrderOption(override val defaultValue: SortOrder) : FilterOption<SortOrder> {
    override var currentValue: SortOrder = defaultValue

    override fun modifyFilterState(filterState: FilterState) = filterState.copy(sortOrder = currentValue)

    @Composable
    override fun Render(onChanged: () -> Unit) {
        val sortOrderState = remember(defaultValue) { mutableStateOf(currentValue) }
        FilterSectionTitle(
            painter = rememberVectorPainter(image = Icons.Default.SwapVert),
            title = R.string.sort_order
        )
        val sortOrderValues = SortOrder.values().toList()
        FilterGrid(items = sortOrderValues) { index, _ ->
            val sortOrder = sortOrderValues[index]
            val selected = sortOrderState.value == sortOrder
            FilterRadioItem(title = stringResource(id = sortOrder.titleResId), selected = selected) {
                currentValue = sortOrder
                sortOrderState.value = currentValue
                onChanged()
            }
        }
        FilterSectionDivider()
    }
}