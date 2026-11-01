package com.haitham.dailyworkout.data.repository

import com.haitham.dailyworkout.domain.model.Exercise
import com.haitham.dailyworkout.domain.model.ExerciseUnit
import com.haitham.dailyworkout.domain.model.Workout
import com.haitham.dailyworkout.domain.repository.WorkoutRepository
import javax.inject.Inject

class SampleWorkoutRepository @Inject constructor() : WorkoutRepository {

    override fun getTodayWorkout(): Workout {
        return Workout(
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
    }
}