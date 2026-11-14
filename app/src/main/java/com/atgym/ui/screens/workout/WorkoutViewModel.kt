package com.atgym.ui.screens.workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.atgym.data.database.AtGymDatabase
import com.atgym.data.database.entity.Exercise
import com.atgym.data.database.entity.WorkoutLog
import com.atgym.data.repository.ExerciseRepository
import com.atgym.data.repository.WorkoutRepository
import com.atgym.util.DateUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AtGymDatabase.getDatabase(application)
    private val exerciseRepository = ExerciseRepository(db.exerciseDao())
    private val workoutRepository = WorkoutRepository(db.workoutLogDao(), db.setCompletionDao())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedMuscleGroup = MutableStateFlow("All")
    val selectedMuscleGroup: StateFlow<String> = _selectedMuscleGroup.asStateFlow()

    val muscleGroups = listOf("All", "Chest", "Back", "Shoulders", "Legs", "Arms", "Core", "Full Body")

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredExercises: StateFlow<List<Exercise>> = combine(
        _searchQuery,
        _selectedMuscleGroup
    ) { query, group ->
        query to group
    }.flatMapLatest { (query, group) ->
        if (query.isNotBlank()) {
            exerciseRepository.searchExercises(query)
        } else if (group != "All") {
            exerciseRepository.getByMuscleGroup(group)
        } else {
            exerciseRepository.allExercises
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onMuscleGroupSelected(group: String) {
        _selectedMuscleGroup.value = group
    }

    fun logWorkoutSet(
        exerciseId: Int,
        exerciseName: String,
        setNumber: Int,
        reps: Int,
        weightKg: Float,
        isFailure: Boolean = false
    ) {
        viewModelScope.launch {
            val log = WorkoutLog(
                exerciseId = exerciseId,
                exerciseName = exerciseName,
                date = DateUtils.todayDateString(),
                setNumber = setNumber,
                reps = reps,
                weightKg = weightKg,
                isCompleted = true,
                isFailureSet = isFailure
            )
            workoutRepository.logSet(log)
        }
    }

    fun getExerciseHistory(exerciseId: Int): Flow<List<WorkoutLog>> {
        return workoutRepository.getLogsForExercise(exerciseId)
    }
}
