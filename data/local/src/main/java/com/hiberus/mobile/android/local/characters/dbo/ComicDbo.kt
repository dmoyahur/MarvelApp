package com.hiberus.mobile.android.local.characters.dbo

import androidx.room.*

@Entity(
    tableName = "Comics",
    foreignKeys = [ForeignKey(
        entity = CharacterDbo::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("characterId")]
)
data class ComicDbo(
    @PrimaryKey(autoGenerate = true)
    val comicId: Long,
    val characterId: Int,
    val name: String,
    val resourceURI: String
) {
    @Ignore
    constructor(characterId: Int, name: String, resourceURI: String) :
            this(0, characterId, name, resourceURI)
}
