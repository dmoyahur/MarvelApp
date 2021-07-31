package com.hiberus.mobile.android.domain.repository

import com.hiberus.mobile.android.model.characters.CharacterBo
import com.hiberus.mobile.android.model.AsyncResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getCharacters(
        offset: Int,
        pageSize: Int,
        forceRefresh: Boolean
    ): Flow<AsyncResult<List<CharacterBo>>>

    suspend fun getCharacterDetail(id: Int): Flow<AsyncResult<CharacterBo>>
}