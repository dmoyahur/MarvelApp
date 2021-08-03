package com.hiberus.mobile.android.repository.characters

import com.hiberus.mobile.android.data.datasource.appsession.AppSessionDataSource
import com.hiberus.mobile.android.data.datasource.characters.CharactersLocalDataSource
import com.hiberus.mobile.android.data.datasource.characters.CharactersRemoteDataSource
import com.hiberus.mobile.android.domain.repository.CharactersRepository
import com.hiberus.mobile.android.model.AsyncResult
import com.hiberus.mobile.android.model.characters.CharacterBo
import com.hiberus.mobile.android.model.error.AsyncError
import com.hiberus.mobile.android.model.error.AsyncException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersDataRepository(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val localDataSource: CharactersLocalDataSource,
    private val sessionDataSource: AppSessionDataSource
) : CharactersRepository {

    override suspend fun getCharacters(
        offset: Int,
        pageSize: Int,
        forceRefresh: Boolean
    ): Flow<AsyncResult<List<CharacterBo>>> = flow {
        if (forceRefresh
            || sessionDataSource.isExpiredTime()
            || !localDataSource.isOffsetUpdated(offset)
        ) {
            try {
                val characters = remoteDataSource.getCharacters(offset, pageSize)
                localDataSource.saveCharacters(characters, offset)
                sessionDataSource.saveLastOpenTime(System.currentTimeMillis())
            } catch (e: Exception) {
                val asyncError = (e as? AsyncException)?.asyncError
                    ?: AsyncError.UnknownError("Unknown error", e)
                emit(AsyncResult.Error(asyncError, null))
            }
        }
        emit(AsyncResult.Success(localDataSource.getCharacters()))
    }

    override suspend fun getCharacterDetail(id: Int): Flow<AsyncResult<CharacterBo>> = flow {
        try {
            val character = remoteDataSource.getCharacterDetail(id)
            localDataSource.saveCharacterDetail(character)
        } catch (e: Exception) {
            val asyncError = (e as? AsyncException)?.asyncError
                ?: AsyncError.UnknownError("Unknown error", e)
            emit(AsyncResult.Error(asyncError, null))
        }
        emit(AsyncResult.Success(localDataSource.getCharacter(id)))
    }
}