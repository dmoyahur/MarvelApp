package com.hiberus.mobile.android.repository.di

import com.hiberus.mobile.android.repository.characters.CharactersRepository
import com.hiberus.mobile.android.repository.characters.CharactersRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<CharactersRepository> {
        CharactersRepositoryImpl(remoteDataSource = get(), localDataSource = get())
    }
}