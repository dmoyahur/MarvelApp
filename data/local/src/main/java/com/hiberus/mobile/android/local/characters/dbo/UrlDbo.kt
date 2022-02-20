package com.hiberus.mobile.android.local.characters.dbo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Urls",
    foreignKeys = [ForeignKey(
        entity = CharacterDbo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("characterId")]
)
data class UrlDbo(
    @PrimaryKey(autoGenerate = true)
    val urlId: Long,
    val characterId: Int,
    val type: String,
    val url: String
) {
    @Ignore
    constructor(characterId: Int, type: String, url: String) :
            this(0, characterId, type, url)
}
