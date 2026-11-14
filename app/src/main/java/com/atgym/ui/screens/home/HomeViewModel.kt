package com.atgym.ui.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.atgym.data.database.AtGymDatabase
import com.atgym.data.database.entity.UserProfile
import com.atgym.data.repository.UserRepository
import com.atgym.data.repository.WorkoutRepository
import com.atgym.util.BmiCalculator
import com.atgym.util.DateUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val selectedDayIndex: Int = DateUtils.todayDayIndex(),
    val todayIndex: Int = DateUtils.todayDayIndex(),
    val completedSetKeys: Set<String> = emptySet(),
    val weightKg: Float = 70f,
    val heightCm: Float = 175f,
    val bmi: Float = 22.9f,
    val bmiCategory: String = "Normal",
    val weeklyCompletionCount: Int = 0,
    val totalWorkoutDays: Int = 0
)

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AtGymDatabase.getDatabase(application)
    private val userRepository = UserRepository(db.userProfileDao())
    private val workoutRepository = WorkoutRepository(db.workoutLogDao(), db.setCompletionDao())

    private val currentWeekStart = DateUtils.currentWeekStart()

    private val _selectedDayIndex = MutableStateFlow(DateUtils.todayDayIndex())
    val selectedDayIndex: StateFlow<Int> = _selectedDayIndex.asStateFlow()

    val todayIndex: Int = DateUtils.todayDayIndex()

    private val userProfileFlow = userRepository.profile.map { profile ->
        profile ?: UserProfile(weightKg = 70f, heightCm = 175f)
    }

    private val completedSetsFlow = workoutRepository.getCompletionsForWeek(currentWeekStart).map { list ->
        list.map { "${it.dayIndex}-${it.exerciseName}-${it.setNumber}" }.toSet()
    }

    private val weeklyCountFlow = workoutRepository.getWeeklyCompletionCount(currentWeekStart)
    private val totalWorkoutDaysFlow = workoutRepository.getTotalWorkoutDays()

    val uiState: StateFlow<HomeUiState> = combine(
        _selectedDayIndex,
        completedSetsFlow,
        userProfileFlow,
        weeklyCountFlow,
        totalWorkoutDaysFlow
    ) { dayIndex, setKeys, profile, weeklyCount, totalDays ->
        val bmiVal = BmiCalculator.calculate(profile.weightKg, profile.heightCm)
        val bmiCat = BmiCalculator.getCategory(bmiVal)

        HomeUiState(
            selectedDayIndex = dayIndex,
            todayIndex = todayIndex,
            completedSetKeys = setKeys,
            weightKg = profile.weightKg,
            heightCm = profile.heightCm,
            bmi = bmiVal,
            bmiCategory = bmiCat,
            weeklyCompletionCount = weeklyCount,
            totalWorkoutDays = totalDays
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )

    fun selectDay(index: Int) {
        _selectedDayIndex.value = index
    }

    fun toggleSet(dayIndex: Int, exerciseName: String, setNumber: Int) {
        viewModelScope.launch {
            workoutRepository.toggleSetCompletion(
                dayIndex = dayIndex,
                exerciseName = exerciseName,
                setNumber = setNumber,
                weekStart = currentWeekStart
            )
        }
    }

    fun clearDay(dayIndex: Int) {
        viewModelScope.launch {
            workoutRepository.clearDay(dayIndex, currentWeekStart)
        }
    }

    fun updateWeight(weight: Float) {
        viewModelScope.launch {
            val current = uiState.value
            userRepository.updateProfile(
                UserProfile(
                    id = 1,
                    weightKg = weight,
                    heightCm = current.heightCm,
                    lastUpdated = DateUtils.todayDateString()
                )
            )
        }
    }

    fun updateHeight(height: Float) {
        viewModelScope.launch {
            val current = uiState.value
            userRepository.updateProfile(
                UserProfile(
                    id = 1,
                    weightKg = current.weightKg,
                    heightCm = height,
                    lastUpdated = DateUtils.todayDateString()
                )
            )
        }
    }
}
