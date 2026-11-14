package com.atgym.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val id: Int = 1,       // singleton row
    val weightKg: Float = 70f,
    val heightCm: Float = 170f,
    val targetWeightKg: Float = 70f,
    val name: String = "Athlete",
    val lastUpdated: String = ""
)
