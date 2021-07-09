package com.hiberus.mobile.android.remote.characters

import com.hiberus.mobile.android.data.datasource.characters.CharactersRemoteDataSource
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.remote.error.RemoteErrorManager
import com.hiberus.mobile.android.remote.characters.mapper.toBo

class CharactersRemoteDataSourceImpl(
    private val charactersService: CharactersService
) : CharactersRemoteDataSource {

    override suspend fun getCharacters(): List<CharacterBo> {
        return RemoteErrorManager.wrap {
            charactersService.getCharacters().toBo()
        }
    }
}