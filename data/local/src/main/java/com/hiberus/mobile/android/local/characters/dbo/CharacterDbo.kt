package com.hiberus.mobile.android.local.characters.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Characters")
data class CharacterDbo(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val imageExtension: String,
    val imagePath: String
)