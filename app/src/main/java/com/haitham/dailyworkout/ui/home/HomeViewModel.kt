package com.haitham.dailyworkout.ui.home

import androidx.lifecycle.ViewModel
import com.haitham.dailyworkout.domain.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            workout = workoutRepository.getTodayWorkout()
        )
    )

    val uiState: StateFlow<HomeUiState> =
        _uiState.asStateFlow()

    fun onExerciseCompleted(exerciseId: String) {
        _uiState.update { currentState ->
            currentState.copy(
                completedExerciseIds =
                    currentState.completedExerciseIds + exerciseId
            )
        }
    }
}