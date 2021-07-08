package com.hiberus.mobile.android.local.characters.dbo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "Series",
    foreignKeys = [ForeignKey(
        entity = CharacterDbo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SerieDbo(
    @PrimaryKey(autoGenerate = true)
    val serieId: Long,
    val characterId: Int,
    val name: String,
    val resourceURI: String
) {
    @Ignore
    constructor(characterId: Int, name: String, resourceURI: String) :
            this(0, characterId, name, resourceURI)
}