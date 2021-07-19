package com.hiberus.mobile.android.marvelapp

import android.content.Intent
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.hiberus.mobile.android.domain.characters.CharacterDetailUseCase
import com.hiberus.mobile.android.domain.characters.CharacterDetailUseCaseImpl
import com.hiberus.mobile.android.marvelapp.characters.detail.CharacterDetailFragment
import com.hiberus.mobile.android.marvelapp.characters.detail.CharacterDetailFragmentArgs
import com.hiberus.mobile.android.marvelapp.util.DataFactory.makeCharacterVo
import com.hiberus.mobile.android.repository.characters.CharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.not
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
@ExperimentalCoroutinesApi
class CharacterDetailFragmentTest {

    private lateinit var charactersRepository: CharactersRepository
    private lateinit var charactersDetailUseCase: CharacterDetailUseCase

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

    @Before
    fun setup() {
        charactersRepository = FakeCharactersTestRepository()
        charactersDetailUseCase = CharacterDetailUseCaseImpl(charactersRepository)
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.hiberus.mobile.android.marvelapp", appContext.packageName)
    }

    @Test
    fun `should hide character description at startup`() = runBlockingTest {
        val bundle = CharacterDetailFragmentArgs(makeCharacterVo()).toBundle()
        val scenario = launchFragmentInContainer<CharacterDetailFragment>(bundle, R.style.Theme_MarvelApp)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Espresso.onView(withId(R.id.tv_character_detail_description))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
    }
}