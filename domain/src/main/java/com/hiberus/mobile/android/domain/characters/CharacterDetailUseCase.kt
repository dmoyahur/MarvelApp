package com.hiberus.mobile.android.domain.characters

import com.hiberus.mobile.android.domain.repository.CharactersRepository
import com.hiberus.mobile.android.model.AsyncResult
import com.hiberus.mobile.android.model.characters.CharacterBo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface CharacterDetailUseCase {

    suspend operator fun invoke(id: Int): Flow<AsyncResult<CharacterBo>>
}

class CharacterDetailUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : CharacterDetailUseCase {

    override suspend operator fun invoke(id: Int): Flow<AsyncResult<CharacterBo>> =
        withContext(Dispatchers.IO) {
            charactersRepository.getCharacterDetail(id)
        }
}