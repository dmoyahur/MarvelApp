package com.hiberus.mobile.android.remote.characters.dto

data class EventListDto(
    val available: String,
    val collectionURI: String,
    val items: List<EventSummaryDto>,
    val returned: String
)