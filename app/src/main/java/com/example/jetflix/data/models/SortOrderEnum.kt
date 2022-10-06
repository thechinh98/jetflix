package com.example.jetflix.data.models

import androidx.annotation.StringRes
import com.example.jetflix.R
import kotlinx.serialization.Serializable

@Serializable
enum class SortOrderEnum(@StringRes val titleResId: Int, val order: String) {
    DESCENDING(R.string.sort_order_descending, "desc"),
    ASCENDING(R.string.sort_order_ascending, "asc")
}
