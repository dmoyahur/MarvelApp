package com.hiberus.mobile.android.marvelapp.common.log.helpers

interface CrashLibrary {

    fun log(priority: Int, tag: String?, message: String)

    fun logException(throwable: Throwable)

    fun setUser(userId: String)
}
