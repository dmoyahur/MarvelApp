package com.hiberus.mobile.android.remote

import com.hiberus.mobile.android.data.datasource.characters.CharactersRemoteDataSource
import com.hiberus.mobile.android.model.error.AsyncError
import com.hiberus.mobile.android.model.error.AsyncException
import com.hiberus.mobile.android.remote.characters.CharactersService
import com.hiberus.mobile.android.remote.di.remoteModule
import com.hiberus.mobile.android.remote.utils.readResourceFile
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import java.net.HttpURLConnection

class CharactersRemoteDataSourceIntegrationTest: KoinTest {

    private val charactersService: CharactersService by inject(CharactersService::class.java)
    private val charactersRemoteDataSource: CharactersRemoteDataSource
        by inject(CharactersRemoteDataSource::class.java)
    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start()
        startKoin {
            modules(remoteModule)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        stopKoin()
    }

    @Test
    fun `should return characters when request has been successful`() = runBlocking {
        mockWebServer.apply {
            enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readResourceFile("characters.json"))
            )
        }

        val actualCharacters = charactersRemoteDataSource.getCharacters(0, 5)
        val request = mockWebServer.takeRequest()

        assertEquals("/v1/public/characters", request.path)
        assertEquals(request.body, actualCharacters)
    }

    @Test
    fun `should throw AsyncException when request has not been successful`() = runBlocking<Unit> {
        mockWebServer.apply {
            enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                    .setBody(readResourceFile("characters.json")))
        }

        try {
            charactersRemoteDataSource.getCharacters(0, 5)
        } catch (e: AsyncException) {
            val asyncError = AsyncError.ServerError(
                code = 400,
                debugMessage = mockWebServer.url("").toString() + ""
            )
            assertEquals(asyncError, e.asyncError)
        }
    }
}