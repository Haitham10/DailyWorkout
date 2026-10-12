package com.haitham.dailyworkout.domain.model

data class Exercise(
    val id: String,
    val name: String,
    val target: Int,
    val unit: ExerciseUnit
)