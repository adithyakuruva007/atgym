package com.atgym.ui.screens.cardio

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.atgym.data.database.AtGymDatabase
import com.atgym.data.database.entity.CardioLog
import com.atgym.data.repository.CardioRepository
import com.atgym.util.DateUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CardioActivity(
    val name: String,
    val category: String, // "LISS", "HIIT", "Steady State"
    val durationRecommendation: String,
    val estimatedCalories: String,
    val intensity: String, // "Low", "Medium", "High"
    val benefit: String
)

val CARDIO_ACTIVITIES = listOf(
    // LISS
    CardioActivity("Incline Treadmill Walk", "LISS", "30-45 min", "200-350 kcal", "Low", "Preserves muscle, burns fat, minimal joint stress"),
    CardioActivity("Outdoor Brisk Walk", "LISS", "40-60 min", "180-300 kcal", "Low", "Active recovery, improves mental health"),
    CardioActivity("Light Stationary Cycling", "LISS", "30-45 min", "200-350 kcal", "Low", "Flushes leg lactic acid, low impact"),
    CardioActivity("Easy Swimming", "LISS", "30-45 min", "250-400 kcal", "Low", "Full body decompression, zero joint impact"),
    CardioActivity("Elliptical (Smooth Pace)", "LISS", "30-40 min", "220-320 kcal", "Low", "Gentle full body aerobic stimulation"),

    // HIIT
    CardioActivity("Sprint Intervals (30s on / 60s off)", "HIIT", "15-20 min", "250-400 kcal", "High", "Maximum calorie burn & EPOC afterburn"),
    CardioActivity("Assault Bike Sprints", "HIIT", "12-18 min", "200-380 kcal", "High", "Brutal full-body conditioning"),
    CardioActivity("Battle Ropes Circuit", "HIIT", "15-20 min", "200-350 kcal", "High", "Upper body cardio + shoulder endurance"),
    CardioActivity("Jump Rope HIIT", "HIIT", "15-20 min", "200-350 kcal", "High", "Foot speed, calves and coordination"),
    CardioActivity("Burpee & Kettlebell Swings", "HIIT", "15-25 min", "250-450 kcal", "High", "Total body explosive power and stamina"),

    // Steady State
    CardioActivity("StairMaster Climbing", "Steady State", "20-30 min", "250-400 kcal", "Medium", "Builds glute and quad endurance"),
    CardioActivity("Rowing Machine", "Steady State", "20-30 min", "250-420 kcal", "Medium", "Back and posterior chain aerobic power"),
    CardioActivity("5K Tempo Jog", "Steady State", "25-35 min", "300-450 kcal", "Medium", "Aerobic engine and lung capacity"),
    CardioActivity("Road / Spin Cycling", "Steady State", "30-45 min", "280-450 kcal", "Medium", "Sustained leg power and cardio threshold")
)

data class CardioUiState(
    val selectedCategory: String = "LISS",
    val activities: List<CardioActivity> = emptyList(),
    val logs: List<CardioLog> = emptyList(),
    val weeklyMinutes: Int = 0,
    val weeklyCalories: Int = 0
)

class CardioViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AtGymDatabase.getDatabase(application)
    private val cardioRepository = CardioRepository(db.cardioLogDao())

    private val _selectedCategory = MutableStateFlow("LISS")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val currentWeekStart = DateUtils.currentWeekStart()

    val categories = listOf("LISS", "HIIT", "Steady State")

    val uiState: StateFlow<CardioUiState> = combine(
        _selectedCategory,
        cardioRepository.allLogs,
        cardioRepository.getTotalMinutesSince(currentWeekStart),
        cardioRepository.getTotalCaloriesSince(currentWeekStart)
    ) { category, logs, minutes, calories ->
        val filteredActivities = CARDIO_ACTIVITIES.filter { it.category == category }
        CardioUiState(
            selectedCategory = category,
            activities = filteredActivities,
            logs = logs,
            weeklyMinutes = minutes ?: 0,
            weeklyCalories = calories ?: 0
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CardioUiState()
    )

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun logCardio(
        activityName: String,
        category: String,
        durationMinutes: Int,
        distanceKm: Float,
        calories: Int,
        intensity: String,
        notes: String
    ) {
        viewModelScope.launch {
            val log = CardioLog(
                activityName = activityName,
                category = category,
                date = DateUtils.todayDateString(),
                durationMinutes = durationMinutes,
                distanceKm = distanceKm,
                caloriesBurned = calories,
                intensity = intensity,
                notes = notes
            )
            cardioRepository.logCardio(log)
        }
    }

    fun deleteLog(log: CardioLog) {
        viewModelScope.launch {
            cardioRepository.delete(log)
        }
    }
}
