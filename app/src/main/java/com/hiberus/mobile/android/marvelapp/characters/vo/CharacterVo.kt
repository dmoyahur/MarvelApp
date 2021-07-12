package com.hiberus.mobile.android.marvelapp.characters.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterVo(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnailPath: String,
    val thumbnailExtension: String
) : Parcelable