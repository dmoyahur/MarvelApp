package com.hiberus.mobile.android.marvelapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.hiberus.mobile.android.marvelapp.characters.list.CharactersListFragment
import com.hiberus.mobile.android.repository.characters.CharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
@ExperimentalCoroutinesApi
class CharactersListFragmentTest {

    private lateinit var charactersRepository: CharactersRepository

    @Rule
    @JvmField
    val activityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java)
                .apply {
                    putExtra("TEST", "TEST")
                }
        }
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.hiberus.mobile.android.marvelapp", appContext.packageName)
    }

    @Before
    fun setUp() {
        charactersRepository = FakeCharactersTestRepository()
    }

    @Test
    fun `should show characters list when launch fragment`() = runBlockingTest {
        val scenario = launchFragmentInContainer<CharactersListFragment>(Bundle(), R.style.Theme_MarvelApp)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
        onView(withId(R.id.rv_characters_list)).check(matches(isDisplayed()))
    }
}