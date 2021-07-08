package com.hiberus.mobile.android.local.characters.dbo

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithUrlsRelation(
    @Embedded val character: CharacterDbo,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val urls: List<UrlDbo>
)