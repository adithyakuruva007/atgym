package com.atgym.data.database.entity

import androidx.room.Entity

@Entity(tableName = "set_completions", primaryKeys = ["dayIndex", "exerciseName", "setNumber", "weekStart"])
data class SetCompletion(
    val dayIndex: Int,
    val exerciseName: String,
    val setNumber: Int,
    val weekStart: String,      // "yyyy-MM-dd" of Monday
    val isCompleted: Boolean = true
)
