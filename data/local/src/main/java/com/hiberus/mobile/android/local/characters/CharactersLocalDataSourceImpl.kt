package com.hiberus.mobile.android.local.characters

import com.hiberus.mobile.android.data.datasource.characters.CharactersLocalDataSource
import com.hiberus.mobile.android.local.characters.mapper.toBo
import com.hiberus.mobile.android.local.characters.mapper.toDbo
import com.hiberus.mobile.android.model.characters.bo.CharacterBo

class CharactersLocalDataSourceImpl(
    private val charactersDao: CharactersDao
) : CharactersLocalDataSource {

    override suspend fun getCharacters(): List<CharacterBo> =
        charactersDao.getAllCharacters()?.toBo() ?: emptyList()

    override suspend fun getCharacter(id: Long): CharacterBo? =
        charactersDao.getCharacter(id)?.toBo()

    override suspend fun saveCharacters(characters: List<CharacterBo>) =
        charactersDao.insertCharactersList(characters.toDbo())
}