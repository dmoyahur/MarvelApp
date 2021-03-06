package com.hiberus.mobile.android.remote.characters

import com.hiberus.mobile.android.remote.characters.dto.CharacterDataWrapperDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com:443"
    }

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") pageSize: Int
    ): CharacterDataWrapperDto

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") id: Int
    ): CharacterDataWrapperDto
}