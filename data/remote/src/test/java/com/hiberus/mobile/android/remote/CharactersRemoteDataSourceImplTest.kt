package com.hiberus.mobile.android.remote

import com.hiberus.mobile.android.data.datasource.characters.CharactersRemoteDataSource
import com.hiberus.mobile.android.model.characters.error.AsyncError
import com.hiberus.mobile.android.model.characters.error.AsyncException
import com.hiberus.mobile.android.remote.characters.CharactersRemoteDataSourceImpl
import com.hiberus.mobile.android.remote.characters.CharactersService
import com.hiberus.mobile.android.remote.factory.DataFactory
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class CharactersRemoteDataSourceImplTest {

    private val charactersService: CharactersService = mock()

    private lateinit var charactersRemoteDataSource: CharactersRemoteDataSource

    @Before
    fun setUp() {
        charactersRemoteDataSource = CharactersRemoteDataSourceImpl(charactersService)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(charactersService)
    }

    @Test
    fun `should return characters when request has been successful`() = runBlockingTest {
        whenever(charactersService.getCharacters(0, 5)) doReturn
                DataFactory.makeCharacterDataWraperDtoResponse(5)

        val actualCharacters = charactersRemoteDataSource.getCharacters(0, 5)
        val expectedCharacters = DataFactory.makeCharacterBoListResponse(5)

        verify(charactersService).getCharacters(0, 5)
        assertEquals(expectedCharacters, actualCharacters)
    }

    @Test
    fun `should throw AsyncException when request has not been successful`() = runBlockingTest {
        whenever(charactersService.getCharacters(0, 100)) doThrow HttpException(
            Response.error<Unit>(HttpURLConnection.HTTP_INTERNAL_ERROR, "".toResponseBody(null))
        )

        try {
            charactersRemoteDataSource.getCharacters(0, 100)
        } catch (e: AsyncException) {
            verify(charactersService).getCharacters(0, 100)
            val asyncError = AsyncError.ServerError(
                code = 500,
                debugMessage = "http://localhost/"
            )
            assertEquals(asyncError, e.asyncError)
        }
    }
}