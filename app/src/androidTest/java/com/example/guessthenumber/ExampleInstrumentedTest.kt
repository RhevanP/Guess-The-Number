package com.example.guessthenumber

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.regex.Pattern.matches

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.guessthenumber", appContext.packageName)
    }

    @Test
    fun guessBelowSolution(){
        onView(withId(R.id.guess))
            .perform(click())
            .perform(typeText("2"))
        onView(withId(R.id.validateguess))
            .perform(click())
            .check(matches(withText("2 tries left")))
        onView(withId(R.id.historyOfGuesses))
            .check(matches(withText(">2")))
        onView(withId(R.id.text_hint))
            .check(matches(withText(R.string.solution_is_higher)))
    }

    @Test
    fun guessAboveSolution(){
        onView(withId(R.id.guess))
            .perform(click())
            .perform(typeText("10"))
        onView(withId(R.id.validateguess))
            .perform(click())
            .check(matches(withText("2 tries left")))
        onView(withId(R.id.historyOfGuesses))
            .check(matches(withText("<10")))
        onView(withId(R.id.text_hint))
            .check(matches(withText(R.string.solution_is_lower)))
    }

    @Test
    fun guessSolution(){ // Solution is 5 when testing the script, change it in the randomNumber.kt file
        onView(withId(R.id.guess))
            .perform(click())
            .perform(typeText("5"))
        onView(withId(R.id.validateguess))
            .perform(click())
            .check(matches(withText("You found the answer!")))
        onView(withId(R.id.text_hint))
            .check(matches(withText(R.string.solution_found)))
    }
}
