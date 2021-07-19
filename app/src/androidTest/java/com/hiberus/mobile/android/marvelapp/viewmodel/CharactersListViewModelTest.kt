package com.hiberus.mobile.android.marvelapp.viewmodel

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hiberus.mobile.android.domain.characters.CharactersUseCase
import com.hiberus.mobile.android.marvelapp.characters.list.CharactersListViewModel
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@ExperimentalCoroutinesApi
class CharactersListViewModelTest {

    private val charactersUseCase = mock<CharactersUseCase>()
    private lateinit var charactersListViewModel: CharactersListViewModel

    @Before
    fun setUp() {
        charactersListViewModel = CharactersListViewModel(charactersUseCase)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(charactersUseCase)
    }

    @Test
    fun `should request all characters at startup`() = runBlockingTest {
        whenever(charactersUseCase()) doReturn emptyFlow()

        charactersListViewModel.getCharacters()

        verify(charactersUseCase)()
    }
}