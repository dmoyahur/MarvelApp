package com.hiberus.mobile.android.data.datasource.characters

import com.hiberus.mobile.android.model.characters.bo.CharacterBo

interface CharactersLocalDataSource {

    suspend fun getCharacters(): List<CharacterBo>

    suspend fun getCharacter(id: Long): CharacterBo?

    suspend fun saveCharacters(characters: List<CharacterBo>, offset: Int)

    suspend fun isOffsetUpdated(offset: Int): Boolean
}