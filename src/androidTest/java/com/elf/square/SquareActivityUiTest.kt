package com.elf.square

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented UI test for SquareActivity.
 */
@RunWith(AndroidJUnit4::class)
class SquareActivityUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<SquareActivity>()

    @Test
    fun app_launches_and_displays_title() {
        // Verify that the TopAppBar title is displayed
        composeTestRule.onNodeWithText("Square Repositories").assertExists()
    }
}
