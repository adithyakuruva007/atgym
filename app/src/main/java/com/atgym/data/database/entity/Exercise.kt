package com.atgym.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val muscleGroup: String,    // "Chest", "Back", "Shoulders", "Legs", "Arms", "Core", "Full Body"
    val equipment: String,       // "Barbell", "Dumbbell", "Cable", "Machine", "Bodyweight", "Kettlebell"
    val description: String = "",
    val isCompound: Boolean = false
)
