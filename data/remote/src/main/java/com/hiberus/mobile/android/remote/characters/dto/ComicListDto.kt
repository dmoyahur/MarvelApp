package com.hiberus.mobile.android.remote.characters.dto

data class ComicListDto(
    val available: String,
    val collectionURI: String,
    val items: List<ComicSummaryDto>,
    val returned: String
)