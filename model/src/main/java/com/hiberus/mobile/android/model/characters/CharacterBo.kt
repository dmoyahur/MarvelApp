package com.hiberus.mobile.android.model.characters

import java.util.*

data class CharacterBo(
    val id: Int,
    val name: String,
    val description: String,
    var modified: Date,
    val resourceURI: String,
    val thumbnail: ImageBo,
    val urls: List<UrlBo>,
    val comics: List<ComicBo>,
    val stories: List<StoryBo>,
    val events: List<EventBo>,
    val series: List<SerieBo>,
)