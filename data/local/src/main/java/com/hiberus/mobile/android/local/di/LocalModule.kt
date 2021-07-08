package com.hiberus.mobile.android.local.di

import com.hiberus.mobile.android.data.datasource.characters.CharactersLocalDataSource
import com.hiberus.mobile.android.local.characters.CharactersDatabase
import com.hiberus.mobile.android.local.characters.CharactersLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { CharactersDatabase.getDatabase(androidContext()) }
    single { get<CharactersDatabase>().charactersDao() }
    single<CharactersLocalDataSource> { CharactersLocalDataSourceImpl(charactersDao = get()) }
}