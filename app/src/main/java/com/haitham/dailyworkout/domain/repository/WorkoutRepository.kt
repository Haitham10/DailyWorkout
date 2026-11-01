package com.haitham.dailyworkout.domain.repository

import com.haitham.dailyworkout.domain.model.Workout

interface WorkoutRepository {

    fun getTodayWorkout(): Workout
}