package com.hiberus.mobile.android.model.characters.bo

data class CharacterBo(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val thumbnail: ImageBo,
    val urls: List<UrlBo>,
    val comics: List<ComicBo>,
    val stories: List<StoryBo>,
    val events: List<EventBo>,
    val series: List<SerieBo>,
)