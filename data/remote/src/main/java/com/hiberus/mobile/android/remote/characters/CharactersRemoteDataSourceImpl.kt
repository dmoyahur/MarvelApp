package com.hiberus.mobile.android.remote.characters

import com.hiberus.mobile.android.data.datasource.characters.CharactersRemoteDataSource
import com.hiberus.mobile.android.model.characters.CharacterBo
import com.hiberus.mobile.android.remote.error.RemoteErrorManager
import com.hiberus.mobile.android.remote.characters.mapper.toBo

class CharactersRemoteDataSourceImpl(
    private val charactersService: CharactersService
) : CharactersRemoteDataSource {

    override suspend fun getCharacters(offset: Int, pageSize: Int): List<CharacterBo> {
        return RemoteErrorManager.wrap {
            charactersService.getCharacters(offset, pageSize).toBo()
        }
    }

    override suspend fun getCharacterDetail(id: Int): CharacterBo {
        return RemoteErrorManager.wrap {
            charactersService.getCharacterDetail(id).toBo().first()
        }
    }
}