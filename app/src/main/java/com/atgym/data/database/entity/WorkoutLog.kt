package com.atgym.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_logs")
data class WorkoutLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val exerciseId: Int,
    val exerciseName: String,
    val date: String,            // "yyyy-MM-dd"
    val setNumber: Int,
    val reps: Int,
    val weightKg: Float,
    val isCompleted: Boolean = false,
    val isFailureSet: Boolean = false
)
