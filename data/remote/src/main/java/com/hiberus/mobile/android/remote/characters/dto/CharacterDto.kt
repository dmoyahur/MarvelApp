package com.hiberus.mobile.android.remote.characters.dto

data class CharacterDto(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<UrlDto>,
    val thumbnail: ImageDto,
    val comics: ComicListDto,
    val stories: StoryListDto,
    val events: EventListDto,
    val series: SeriesListDto
)