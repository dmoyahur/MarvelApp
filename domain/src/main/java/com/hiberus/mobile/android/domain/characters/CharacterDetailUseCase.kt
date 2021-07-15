package com.hiberus.mobile.android.domain.characters

import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.repository.characters.CharactersRepository
import com.hiberus.mobile.android.repository.util.AsyncResult
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