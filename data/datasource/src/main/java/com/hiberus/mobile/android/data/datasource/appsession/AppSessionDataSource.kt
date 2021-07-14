package com.hiberus.mobile.android.data.datasource.appsession

interface AppSessionDataSource {

    suspend fun saveLastOpenTime(timeMillis: Long): Boolean

    suspend fun getLastOpenTime(): Long?

    suspend fun isExpiredTime(): Boolean
}