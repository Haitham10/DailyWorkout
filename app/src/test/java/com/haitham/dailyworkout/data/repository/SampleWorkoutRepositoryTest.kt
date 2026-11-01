package com.haitham.dailyworkout.data.repository

import com.haitham.dailyworkout.domain.model.Exercise
import com.haitham.dailyworkout.domain.model.ExerciseUnit
import com.haitham.dailyworkout.domain.model.Workout
import com.haitham.dailyworkout.domain.repository.WorkoutRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SampleWorkoutRepositoryTest {

    private val repository: WorkoutRepository =
        SampleWorkoutRepository()

    @Test
    fun `getTodayWorkout returns expected workout`() {
        // Arrange
        val expectedWorkout = Workout(
            id = "beginner_full_body",
            title = "Beginner Full Body",
            exercises = listOf(
                Exercise(
                    id = "push_ups",
                    name = "Push-ups",
                    target = 10,
                    unit = ExerciseUnit.REPETITIONS
                ),
                Exercise(
                    id = "squats",
                    name = "Squats",
                    target = 15,
                    unit = ExerciseUnit.REPETITIONS
                ),
                Exercise(
                    id = "plank",
                    name = "Plank",
                    target = 30,
                    unit = ExerciseUnit.SECONDS
                )
            )
        )

        // Act
        val actualWorkout = repository.getTodayWorkout()

        // Assert
        assertEquals(expectedWorkout, actualWorkout)
    }

    @Test
    fun `getTodayWorkout returns exercises with positive targets`() {
        // Act
        val workout = repository.getTodayWorkout()

        // Assert
        val allTargetsArePositive = workout.exercises.all { exercise ->
            exercise.target > 0
        }

        assertTrue(allTargetsArePositive)
    }
}