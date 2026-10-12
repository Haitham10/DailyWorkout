package com.haitham.dailyworkout.domain.model

data class Workout(
    val id: String,
    val title: String,
    val exercises: List<Exercise>
)