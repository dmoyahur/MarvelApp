package com.hiberus.mobile.android.local.characters

import com.hiberus.mobile.android.data.datasource.characters.CharactersLocalDataSource
import com.hiberus.mobile.android.local.characters.mapper.OffsetMapper
import com.hiberus.mobile.android.local.characters.mapper.toBo
import com.hiberus.mobile.android.local.characters.mapper.toDbo
import com.hiberus.mobile.android.model.characters.CharacterBo

class CharactersLocalDataSourceImpl(
    private val charactersDao: CharactersDao
) : CharactersLocalDataSource {

    override suspend fun getCharacters(): List<CharacterBo> =
        charactersDao.getAllCharacters()?.toBo() ?: emptyList()

    override suspend fun getCharacter(id: Int): CharacterBo? =
        charactersDao.getCharacter(id)?.toBo()

    override suspend fun saveCharacters(characters: List<CharacterBo>, offset: Int) =
        charactersDao.insertCharactersList(characters.toDbo(), OffsetMapper.map(offset))

    override suspend fun saveCharacterDetail(character: CharacterBo) {
        getCharacter(character.id).apply {
            if (this == null || this.modified.before(character.modified)) {
                charactersDao.insertCharacterSummary(character.toDbo())
            }
        }
    }

    override suspend fun isOffsetUpdated(offset: Int): Boolean =
        charactersDao.getOffset()?.let {
            it >= offset
        } ?: false
}
