package com.hiberus.mobile.android.marvelapp.di

import com.hiberus.mobile.android.marvelapp.characters.list.CharactersListAdapter
import com.hiberus.mobile.android.marvelapp.characters.list.CharactersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { CharactersListAdapter() }
    viewModel { CharactersListViewModel(charactersUseCase = get()) }
}
