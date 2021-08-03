package com.hiberus.mobile.android.repository.di

import com.hiberus.mobile.android.domain.repository.CharactersRepository
import com.hiberus.mobile.android.repository.characters.CharactersDataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CharactersRepository> {
        CharactersDataRepository(remoteDataSource = get(), localDataSource = get(), sessionDataSource = get())
    }
}