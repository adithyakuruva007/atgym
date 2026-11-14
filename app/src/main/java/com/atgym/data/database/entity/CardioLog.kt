package com.atgym.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cardio_logs")
data class CardioLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val activityName: String,
    val category: String,        // "LISS", "HIIT", "Steady State"
    val date: String,
    val durationMinutes: Int,
    val distanceKm: Float = 0f,
    val caloriesBurned: Int = 0,
    val intensity: String = "Medium",  // "Low", "Medium", "High"
    val notes: String = ""
)
