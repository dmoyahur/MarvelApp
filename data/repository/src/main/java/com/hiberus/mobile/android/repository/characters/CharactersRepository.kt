package com.hiberus.mobile.android.repository.characters

import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getCharacters(
        offset: Int,
        pageSize: Int,
        forceRefresh: Boolean
    ): Flow<AsyncResult<List<CharacterBo>>>

    suspend fun getCharacterDetail(id: Int): Flow<AsyncResult<CharacterBo>>
}