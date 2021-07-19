package com.hiberus.mobile.android.domain.characters

import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.repository.characters.CharactersRepository
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface CharactersUseCase {

    suspend operator fun invoke(
        offset: Int = 0,
        pageSize: Int = 100,
        forceRefresh: Boolean = false
    ): Flow<AsyncResult<List<CharacterBo>>>
}

class CharactersUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : CharactersUseCase {

    override suspend operator fun invoke(
        offset: Int,
        pageSize: Int,
        forceRefresh: Boolean
    ): Flow<AsyncResult<List<CharacterBo>>> =
        withContext(Dispatchers.IO) {
            charactersRepository.getCharacters(offset, pageSize, forceRefresh)
        }
}