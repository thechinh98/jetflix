package com.example.jetflix.presentation.screens.filter

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetflix.R
import com.example.jetflix.domain.entities.FilterStateEntity
import com.example.jetflix.domain.entities.toFilterOptions

@Composable
fun FilterBottomSheetContent(
    filterState: FilterStateEntity,
    onFilterStateChanged: (FilterStateEntity) -> Unit
) {
    Spacer(modifier = Modifier.padding(top = 4.dp))
    filterState.toFilterOptions().forEach { filterOption ->
        filterOption.Render {
            val newState = filterOption.modifyFilterState(filterState)
            onFilterStateChanged(newState)
        }
    }
}

@Composable
fun FilterHeader(onHideClicked: () -> Unit, onResetClicked: (() -> Unit)? = null) {
    Surface(
        Modifier.fillMaxWidth(),
        elevation = 8.dp,
        color = MaterialTheme.colors.primary
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .padding(end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onHideClicked) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_content_description)
                    )
                }
                Text(
                    text = stringResource(id = R.string.title_filter_bottom_sheet),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.body1,
                )
            }
            if (onResetClicked != null) {
                IconButton(onClick = { onResetClicked() }) {
                    Text(
                        text = stringResource(id = R.string.reset),
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FilterHeaderPreview() {
    FilterHeader(onHideClicked = {}, onResetClicked = {})
}