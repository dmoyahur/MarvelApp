package com.hiberus.mobile.android.remote.characters.dto

data class CharacterDataWrapperDto(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val data: CharacterDataContainerDto,
    val etag: String,
    val status: String
)