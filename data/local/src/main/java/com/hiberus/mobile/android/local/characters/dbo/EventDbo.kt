package com.hiberus.mobile.android.local.characters.dbo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "Events",
    foreignKeys = [ForeignKey(
        entity = CharacterDbo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class EventDbo(
    @PrimaryKey(autoGenerate = true)
    val eventId: Long,
    val characterId: Int,
    val name: String,
    val resourceURI: String
) {
    @Ignore
    constructor(characterId: Int, name: String, resourceURI: String) :
            this(0, characterId, name, resourceURI)
}