package com.haitham.dailyworkout.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.haitham.dailyworkout.domain.model.Exercise
import com.haitham.dailyworkout.domain.model.ExerciseUnit
import com.haitham.dailyworkout.domain.model.Workout
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickingComplete_marksExerciseAsCompleted() {
        val workout = Workout(
            id = "test_workout",
            title = "Test Workout",
            exercises = listOf(
                Exercise(
                    id = "push_ups",
                    name = "Push-ups",
                    target = 10,
                    unit = ExerciseUnit.REPETITIONS
                )
            )
        )

        composeTestRule.setContent {
            val completedExerciseIds = remember {
                mutableStateOf(emptySet<String>())
            }

            HomeScreen(
                uiState = HomeUiState(
                    workout = workout,
                    completedExerciseIds = completedExerciseIds.value
                ),
                onExerciseCompleted = { exerciseId ->
                    completedExerciseIds.value =
                        completedExerciseIds.value + exerciseId
                }
            )
        }

        // Before click
        composeTestRule
            .onNodeWithText("Progress: 0 / 1 completed")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Complete")
            .assertIsDisplayed()

        // User clicks the button
        composeTestRule
            .onNodeWithText("Complete")
            .performClick()

        // After click
        composeTestRule
            .onNodeWithText("Progress: 1 / 1 completed")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Completed")
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }
}