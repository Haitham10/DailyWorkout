package com.haitham.dailyworkout.ui.home

import com.haitham.dailyworkout.domain.model.Workout

data class HomeUiState(
    val workout: Workout,
    val completedExerciseIds: Set<String> = emptySet()
)