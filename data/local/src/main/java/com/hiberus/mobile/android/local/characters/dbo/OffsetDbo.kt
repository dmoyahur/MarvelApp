package com.hiberus.mobile.android.local.characters.dbo

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Offset")
data class OffsetDbo(
    @PrimaryKey
    val id: Int,
    val offset: Int
) {
    @Ignore
    constructor(offset: Int): this(0, offset)
}