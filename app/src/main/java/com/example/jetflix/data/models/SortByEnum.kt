package com.example.jetflix.data.models

import androidx.annotation.StringRes
import com.example.jetflix.R
import kotlinx.serialization.Serializable

@Serializable
enum class SortByEnum(@StringRes val titleResId: Int, val by: String) {
    POPULARITY(R.string.sort_by_popularity, "popularity"),
    VOTE_COUNT(R.string.sort_by_vote_count, "vote_count"),
    VOTE_AVERAGE(R.string.sort_by_vote_average, "vote_average"),
    RELEASE_DATE(R.string.sort_by_release_date, "release_date"),
    ORIGINAL_TITLE(R.string.sort_by_original_title, "original_title"),
    REVENUE(R.string.sort_by_revenue, "revenue")
}
