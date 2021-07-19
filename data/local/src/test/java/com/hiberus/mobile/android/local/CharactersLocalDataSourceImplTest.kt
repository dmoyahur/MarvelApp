package com.hiberus.mobile.android.local

import com.hiberus.mobile.android.local.characters.CharactersDao
import com.hiberus.mobile.android.local.characters.CharactersLocalDataSourceImpl
import com.hiberus.mobile.android.local.factory.DataFactory.makeCharacterBoResponse
import com.hiberus.mobile.android.local.factory.DataFactory.makeCharacterSummaryDboResponse
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersLocalDataSourceImplTest {

    private val charactersDao = mock<CharactersDao>()
    private val charactersLocalDataSourceImpl = CharactersLocalDataSourceImpl(charactersDao)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(charactersDao)
    }

    @Test
    fun `should map and return all characters`() = runBlockingTest {
        val charactersSummary = makeCharacterSummaryDboResponse()
        whenever(charactersDao.getAllCharacters()) doReturn listOf(charactersSummary)

        val actualCharactersSummary = charactersLocalDataSourceImpl.getCharacters()

        verify(charactersDao).getAllCharacters()
        assertEquals(listOf(makeCharacterBoResponse()), actualCharactersSummary)
    }

    @Test
    fun `should map and return requested characters`() = runBlockingTest {
        val characterId = 1
        val character = makeCharacterSummaryDboResponse()
        whenever(charactersDao.getCharacter(characterId)) doReturn character

        val actualCharacter = charactersLocalDataSourceImpl.getCharacter(characterId)

        verify(charactersDao).getCharacter(characterId)
        assertEquals(makeCharacterBoResponse(), actualCharacter)
    }

    @Test
    fun `should return null if does not exist on database`() = runBlockingTest {
        val requestedCharacterId = 1
        whenever(charactersDao.getCharacter(requestedCharacterId)) doReturn null

        val actualCharacter = charactersLocalDataSourceImpl.getCharacter(requestedCharacterId)

        verify(charactersDao).getCharacter(requestedCharacterId)
        assertEquals(null, actualCharacter)
    }

    @Test
    fun `should map characters to store on database`() = runBlockingTest {
        val requestedCharacterId = 2
        val characterBo = makeCharacterBoResponse(requestedCharacterId)
        val characterDbo = makeCharacterSummaryDboResponse(requestedCharacterId)

        charactersLocalDataSourceImpl.saveCharacterDetail(listOf(characterBo))

        verify(charactersDao).getCharacter(requestedCharacterId)
        verify(charactersDao).insertCharacterSummary(characterDbo)
    }

}