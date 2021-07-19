package com.hiberus.mobile.android.local

import android.app.Application
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hiberus.mobile.android.local.characters.CharactersDao
import com.hiberus.mobile.android.local.characters.CharactersDatabase
import com.hiberus.mobile.android.local.characters.CharactersLocalDataSourceImpl
import com.hiberus.mobile.android.local.factory.DataFactory.makeCharacterBoResponse
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
@Config(manifest= Config.NONE, sdk=[Build.VERSION_CODES.P])
class CharactersLocalDataSourceIntegrationTest {
    private lateinit var database: CharactersDatabase
    private lateinit var charactersDao: CharactersDao
    private lateinit var charactersLocalDataSource: CharactersLocalDataSourceImpl

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        database = Room.inMemoryDatabaseBuilder(context, CharactersDatabase::class.java)
            .build()
        charactersDao = database.charactersDao()
        charactersLocalDataSource = CharactersLocalDataSourceImpl(charactersDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `should save and return all characters`() = runBlocking<Unit> {
        val character = makeCharacterDboResponse()

        charactersDao.insertCharacter(character)
        val actualCharacters = charactersLocalDataSource.getCharacters()

        assertEquals(listOf(makeCharacterBoResponse()), actualCharacters)
    }

    @Test
    fun `should save and return requested characters`() = runBlocking {
        val requestedCharacterId = 1
        val character = makeCharacterDboResponse(requestedCharacterId)
        val otherCharacter = makeCharacterDboResponse(2)

        charactersDao.insertCharacter(character)
        charactersDao.insertCharacter(otherCharacter)
        val actualCharacter = charactersLocalDataSource.getCharacter(requestedCharacterId)

        assertEquals(makeCharacterBoResponse(requestedCharacterId), actualCharacter)
    }

    @Test
    fun `should not return requested characters if it does not exist`() = runBlocking {
        val requestedCharacterId = 1
        val otherCharacter = makeCharacterDboResponse(2)

        charactersDao.insertCharacter(otherCharacter)
        val actualCharacter = charactersLocalDataSource.getCharacter(requestedCharacterId)

        assertEquals(null, actualCharacter)
    }
}