package com.hiberus.mobile.android.remote.error

import com.hiberus.mobile.android.model.characters.error.AsyncError
import com.hiberus.mobile.android.model.characters.error.AsyncException
import retrofit2.HttpException
import java.net.UnknownHostException

object  RemoteErrorManager {

    inline fun <T> wrap(function: () -> T): T {
        return try {
            function()
        } catch (e: Throwable) {
            throw AsyncException(processError(e))
        }
    }

    fun processError(throwable: Throwable): AsyncError {
        return when(throwable) {
            is HttpException -> {
                val statusCode = throwable.code()
                val url = throwable.response()?.raw()?.request?.url?.toString() ?: ""
                AsyncError.ServerError(statusCode, url)
            }
            is UnknownHostException -> AsyncError.ConnectionError(throwable.message ?: "Connection error")
//            is JsonDataException -> AsyncError.DataParseError(throwable.message ?: "Parse error")
            else -> AsyncError.UnknownError(throwable.message ?: "Unknown error", throwable)
        }
    }
}