package com.hiberus.mobile.android.local.characters.dbo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Stories",
    foreignKeys = [ForeignKey(
        entity = CharacterDbo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("characterId")]
)
data class StoryDbo(
    @PrimaryKey(autoGenerate = true)
    val storyId: Long,
    val characterId: Int,
    val name: String,
    val resourceURI: String,
    val type: String
) {
    @Ignore
    constructor(characterId: Int, name: String, resourceURI: String, type: String) :
            this(0, characterId, name, resourceURI, type)
}
