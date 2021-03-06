package com.hiberus.mobile.android.data.datasource.characters

import com.hiberus.mobile.android.model.characters.CharacterBo

interface CharactersRemoteDataSource {

    suspend fun getCharacters(offset: Int, pageSize: Int): List<CharacterBo>

    suspend fun getCharacterDetail(id: Int): CharacterBo
}