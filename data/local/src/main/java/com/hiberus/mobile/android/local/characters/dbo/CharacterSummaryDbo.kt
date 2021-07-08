package com.hiberus.mobile.android.local.characters.dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class CharacterSummaryDbo(
    @Embedded val character: CharacterDbo,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val urls: List<UrlDbo>,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val comics: List<ComicDbo>,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val stories: List<StoryDbo>,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val events: List<EventDbo>,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val series: List<SerieDbo>
)