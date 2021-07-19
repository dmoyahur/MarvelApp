package com.hiberus.mobile.android.marvelapp

import com.hiberus.mobile.android.marvelapp.util.DataFactory.makeCharacterBoList
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.repository.characters.CharactersRepository
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharactersTestRepository: CharactersRepository {

    override suspend fun getCharacters(
        offset: Int,
        pageSize: Int,
        forceRefresh: Boolean
    ): Flow<AsyncResult<List<CharacterBo>>> = flow {
        val result = makeCharacterBoList(pageSize)
        emit(AsyncResult.Success(result))
    }

    override suspend fun getCharacterDetail(id: Int): Flow<AsyncResult<CharacterBo>> = flow {
        val result = makeCharacterBoList(1)
        emit(AsyncResult.Success(result.first()))
    }
}