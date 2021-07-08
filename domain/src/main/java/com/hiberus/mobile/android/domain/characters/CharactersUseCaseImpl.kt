package com.hiberus.mobile.android.domain.characters

import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.repository.characters.CharactersRepository
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CharactersUseCaseImpl(
    private val charactersRepository: CharactersRepository
): CharactersUseCase {

    override suspend fun invoke(): Flow<AsyncResult<List<CharacterBo>>> =
        withContext(Dispatchers.IO) {
            charactersRepository.getCharacters(true)
        }
}