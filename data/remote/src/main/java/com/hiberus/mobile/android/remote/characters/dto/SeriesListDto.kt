package com.hiberus.mobile.android.remote.characters.dto

data class SeriesListDto(
    val available: String,
    val collectionURI: String,
    val items: List<SerieSummaryDto>,
    val returned: String
)