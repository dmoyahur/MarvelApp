package com.hiberus.mobile.android.remote.characters.dto

import java.util.*

data class CharacterDto(
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: List<UrlDto>,
    val thumbnail: ImageDto,
    val comics: ComicListDto,
    val stories: StoryListDto,
    val events: EventListDto,
    val series: SeriesListDto
)