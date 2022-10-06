package com.example.jetflix.presentation.screens.filter.option

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.example.jetflix.R
import com.example.jetflix.data.models.SortByEnum
import com.example.jetflix.domain.entities.FilterStateEntity
import com.example.jetflix.presentation.screens.filter.FilterGrid
import com.example.jetflix.presentation.screens.filter.FilterRadioItem
import com.example.jetflix.presentation.screens.filter.FilterSectionDivider
import com.example.jetflix.presentation.screens.filter.FilterSectionTitle

data class SortByOption(override val defaultValue: SortByEnum) : FilterOption<SortByEnum> {
    override var currentValue: SortByEnum = defaultValue

    override fun modifyFilterState(filterState: FilterStateEntity) = filterState.copy(sortBy = currentValue)

    @Composable
    override fun Render(onChanged: () -> Unit) {
        val sortByState = remember(defaultValue) { mutableStateOf(currentValue) }
        FilterSectionTitle(painter = rememberVectorPainter(image = Icons.Default.Sort), title = R.string.sort_by)
        val sortByValues = SortByEnum.values().toList()
        FilterGrid(items = sortByValues) { index, _ ->
            val sortBy = sortByValues[index]
            val selected = sortByState.value == sortBy
            FilterRadioItem(title = stringResource(id = sortBy.titleResId), selected = selected) {
                currentValue = sortBy
                sortByState.value = sortBy
                onChanged()
            }
        }
        FilterSectionDivider()
    }
}