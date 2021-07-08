package com.hiberus.mobile.android.remote.characters.dto

data class StoryListDto(
    val available: String,
    val collectionURI: String,
    val items: List<StorySummaryDto>,
    val returned: String
)