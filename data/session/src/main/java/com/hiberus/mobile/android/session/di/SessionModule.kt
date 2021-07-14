package com.hiberus.mobile.android.session.di

import com.hiberus.mobile.android.data.datasource.appsession.AppSessionDataSource
import com.hiberus.mobile.android.session.SessionData
import com.hiberus.mobile.android.session.appsession.AppSessionDataSourceImpl
import com.hiberus.mobile.android.session.preferences.Preferences
import com.hiberus.mobile.android.session.preferences.PreferencesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sessionModule = module {
    single<Preferences> { PreferencesImpl(androidContext()) }
    single { SessionData(preferences = get()) }
    single<AppSessionDataSource> { AppSessionDataSourceImpl(sessionData = get()) }
}