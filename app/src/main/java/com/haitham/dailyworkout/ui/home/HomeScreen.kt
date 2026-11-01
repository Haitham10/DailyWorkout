package com.haitham.dailyworkout.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.haitham.dailyworkout.domain.model.Exercise
import com.haitham.dailyworkout.domain.model.ExerciseUnit

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onExerciseCompleted = viewModel::onExerciseCompleted
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onExerciseCompleted: (String) -> Unit
) {
    val totalExerciseCount = uiState.workout.exercises.size

    val completedExerciseCount = uiState.workout.exercises.count { exercise ->
        exercise.id in uiState.completedExerciseIds
    }

    val progress = if (totalExerciseCount == 0) {
        0f
    } else {
        (completedExerciseCount.toFloat() / totalExerciseCount)
            .coerceIn(0f, 1f)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Today's Workout",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        item {
            Text(
                text = uiState.workout.title,
                style = MaterialTheme.typography.titleLarge
            )
        }

        item {
            Text(
                text = "Progress: $completedExerciseCount / $totalExerciseCount completed",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        item {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )
        }

        items(
            items = uiState.workout.exercises,
            key = { exercise -> exercise.id }
        ) { exercise ->
            ExerciseCard(
                exercise = exercise,
                isCompleted = exercise.id in uiState.completedExerciseIds,
                onComplete = {
                    onExerciseCompleted(exercise.id)
                }
            )
        }
    }
}

@Composable
private fun ExerciseCard(
    exercise: Exercise,
    isCompleted: Boolean,
    onComplete: () -> Unit
) {
    val unitLabel = when (exercise.unit) {
        ExerciseUnit.REPETITIONS -> "reps"
        ExerciseUnit.SECONDS -> "sec"
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "${exercise.target} $unitLabel",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Button(
                onClick = onComplete,
                enabled = !isCompleted,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isCompleted) {
                        "Completed"
                    } else {
                        "Complete"
                    }
                )
            }
        }
    }
}