package com.hiberus.mobile.android.repository.characters

import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getCharacters(forceRefresh: Boolean = false): Flow<AsyncResult<List<CharacterBo>>>

    suspend fun getCharacter(id: Long): Flow<AsyncResult<CharacterBo>>
}