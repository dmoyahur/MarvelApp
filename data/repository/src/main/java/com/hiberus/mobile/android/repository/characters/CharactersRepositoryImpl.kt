package com.hiberus.mobile.android.repository.characters

import com.hiberus.mobile.android.data.datasource.appsession.AppSessionDataSource
import com.hiberus.mobile.android.data.datasource.characters.CharactersLocalDataSource
import com.hiberus.mobile.android.data.datasource.characters.CharactersRemoteDataSource
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.model.characters.error.AsyncError
import com.hiberus.mobile.android.model.characters.error.AsyncException
import com.hiberus.mobile.android.repository.util.AsyncResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersRepositoryImpl(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val localDataSource: CharactersLocalDataSource,
    private val sessionDataSource: AppSessionDataSource
) : CharactersRepository {

    override suspend fun getCharacters(
        offset: Int,
        pageSize: Int,
        forceRefresh: Boolean
    ): Flow<AsyncResult<List<CharacterBo>>> = flow {
        emit(AsyncResult.Loading(null))
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
}