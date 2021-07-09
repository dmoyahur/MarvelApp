package com.hiberus.mobile.android.marvelapp

import android.app.Application
import com.hiberus.mobile.android.domain.di.domainModule
import com.hiberus.mobile.android.local.di.localModule
import com.hiberus.mobile.android.marvelapp.di.*
import com.hiberus.mobile.android.remote.di.remoteModule
import com.hiberus.mobile.android.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelApplication)
            modules(listOf(
                appModule,
                domainModule,
                repositoryModule,
                localModule,
                remoteModule
            ))
        }
    }
}