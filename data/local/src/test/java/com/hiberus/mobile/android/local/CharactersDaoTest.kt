package com.hiberus.mobile.android.local

import android.app.Application
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hiberus.mobile.android.local.characters.CharactersDao
import com.hiberus.mobile.android.local.characters.CharactersDatabase
import com.hiberus.mobile.android.local.factory.DataFactory.makeCharacterDboResponse
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class CharactersDaoTest {

    private lateinit var database: CharactersDatabase
    private lateinit var charactersDao: CharactersDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        database = Room.inMemoryDatabaseBuilder(context, CharactersDatabase::class.java)
            .build()
        charactersDao = database.charactersDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `should save and return all characters`() = runBlocking {
        val character = makeCharacterDboResponse()

        charactersDao.insertCharacter(character)
        val actualCharacters = charactersDao.getAllCharacters()?.first()?.character

        assertEquals(actualCharacters, character)
    }

    @Test
    fun `should save and return requested characters`() = runBlocking {
        val character = makeCharacterDboResponse(1)
        val otherCharacter = makeCharacterDboResponse(2)

        charactersDao.insertCharacter(character)
        charactersDao.insertCharacter(otherCharacter)
        val actualCharacter = charactersDao.getCharacter(1)?.character

        assertEquals(character, actualCharacter)
    }

    @Test
    fun `should not return requested characters if it does not exist`() = runBlocking {
        val requestedCharacterId = 4
        val otherCharacter = makeCharacterDboResponse(3)

        charactersDao.insertCharacter(otherCharacter)
        val actualCharacter = charactersDao.getCharacter(requestedCharacterId)

        assertEquals(null, actualCharacter)
    }
}