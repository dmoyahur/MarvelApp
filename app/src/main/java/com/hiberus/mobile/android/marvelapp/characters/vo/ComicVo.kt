package com.hiberus.mobile.android.marvelapp.characters.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicVo(
    val resourceUri: String,
    val name: String
): Parcelable