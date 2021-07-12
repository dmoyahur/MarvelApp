package com.hiberus.mobile.android.domain.characters

import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.flow.Flow

interface CharactersUseCase {
    suspend operator fun invoke(currentRankingPage: Int, pageSize: Int): Flow<AsyncResult<List<CharacterBo>>>
}