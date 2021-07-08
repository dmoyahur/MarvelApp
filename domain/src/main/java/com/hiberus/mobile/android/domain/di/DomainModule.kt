package com.hiberus.mobile.android.domain.di

import com.hiberus.mobile.android.domain.characters.CharactersUseCase
import com.hiberus.mobile.android.domain.characters.CharactersUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<CharactersUseCase> { CharactersUseCaseImpl(charactersRepository = get()) }
}
