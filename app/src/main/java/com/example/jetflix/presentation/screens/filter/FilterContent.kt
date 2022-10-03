package com.example.jetflix.presentation.screens.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun FilterHeader(onHideClicked: () -> Unit, onResetClick: (() -> Unit)? = null) {
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
            if (onResetClick != null) {
                IconButton(onClick = { onResetClick() }) {
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
    FilterHeader(onHideClicked = {}, onResetClick = {})
}