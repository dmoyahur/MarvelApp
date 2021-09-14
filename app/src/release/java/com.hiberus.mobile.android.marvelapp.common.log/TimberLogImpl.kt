package com.hiberus.mobile.android.marvelapp.common.log

import com.hiberus.mobile.android.marvelapp.TimberLog
import com.hiberus.mobile.android.marvelapp.common.log.helpers.CrashLibraryTree
import timber.log.Timber

object TimberLogImpl : TimberLog {

    override fun init() {
        init("")
    }

    override fun init(userId: String) {
        Timber.plant(CrashLibraryTree(userId))
    }
}