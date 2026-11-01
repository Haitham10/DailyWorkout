package com.haitham.dailyworkout.ui.home

import com.haitham.dailyworkout.domain.model.Exercise
import com.haitham.dailyworkout.domain.model.ExerciseUnit
import com.haitham.dailyworkout.domain.model.Workout
import com.haitham.dailyworkout.domain.repository.WorkoutRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun `uiState contains workout returned by repository`() {
        val expectedWorkout = createTestWorkout()

        val viewModel = HomeViewModel(
            workoutRepository = FakeWorkoutRepository(expectedWorkout)
        )

        assertEquals(
            expectedWorkout,
            viewModel.uiState.value.workout
        )
    }

    @Test
    fun `onExerciseCompleted adds exercise id to completed ids`() {
        val viewModel = HomeViewModel(
            workoutRepository = FakeWorkoutRepository(
                createTestWorkout()
            )
        )

        viewModel.onExerciseCompleted("test_exercise")

        assertTrue(
            viewModel.uiState.value.completedExerciseIds
                .contains("test_exercise")
        )
    }

    private fun createTestWorkout(): Workout {
        return Workout(
            id = "test_workout",
            title = "Test Workout",
            exercises = listOf(
                Exercise(
                    id = "test_exercise",
                    name = "Test Exercise",
                    target = 12,
                    unit = ExerciseUnit.REPETITIONS
                )
            )
        )
    }
}

private class FakeWorkoutRepository(
    private val workout: Workout
) : WorkoutRepository {

    override fun getTodayWorkout(): Workout {
        return workout
    }
}