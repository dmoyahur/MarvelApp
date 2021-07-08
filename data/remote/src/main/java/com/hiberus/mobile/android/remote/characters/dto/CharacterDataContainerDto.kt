package com.hiberus.mobile.android.remote.characters.dto

data class CharacterDataContainerDto(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<CharacterDto>,
    val total: String
)