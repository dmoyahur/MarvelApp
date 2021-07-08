package com.hiberus.mobile.android.repository.characters

import com.hiberus.mobile.android.data.datasource.characters.CharactersLocalDataSource
import com.hiberus.mobile.android.data.datasource.characters.CharactersRemoteDataSource
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.model.characters.error.AsyncError
import com.hiberus.mobile.android.model.characters.error.AsyncException
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class CharactersRepositoryImpl(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val localDataSource: CharactersLocalDataSource
): CharactersRepository {

    override suspend fun getCharacters(forceRefresh: Boolean): Flow<AsyncResult<List<CharacterBo>>> = flow {
        emit(AsyncResult.Loading(null))
        if (forceRefresh) {
            try {
                val characters = remoteDataSource.getCharacters()
                localDataSource.saveCharacters(characters)
            } catch (e: Exception) {
                val asyncError = (e as? AsyncException)?.asyncError
                    ?: AsyncError.UnknownError("Unknown error", e)
                emit(AsyncResult.Error(asyncError, null))
            }
        }
        emit(AsyncResult.Success(localDataSource.getCharacters()))
    }

    override suspend fun getCharacter(id: Long): Flow<AsyncResult<CharacterBo>> {
        TODO("Not yet implemented")
    }
}