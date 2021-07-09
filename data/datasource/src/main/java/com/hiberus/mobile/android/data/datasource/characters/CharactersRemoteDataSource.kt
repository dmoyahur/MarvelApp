package com.hiberus.mobile.android.data.datasource.characters

import com.hiberus.mobile.android.model.characters.bo.CharacterBo

interface CharactersRemoteDataSource {

    suspend fun getCharacters(): List<CharacterBo>
}