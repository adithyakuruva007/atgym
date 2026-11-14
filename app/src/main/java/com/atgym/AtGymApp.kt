package com.atgym

import android.app.Application
import com.atgym.data.database.AtGymDatabase
import com.atgym.data.database.entity.UserProfile
import com.atgym.data.seed.ExerciseSeeder
import com.atgym.util.DateUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AtGymApp : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val database by lazy { AtGymDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()

        // Seed 100+ exercises and initial user profile if database is fresh
        applicationScope.launch {
            val exerciseDao = database.exerciseDao()
            if (exerciseDao.getCount() == 0) {
                exerciseDao.insertAll(ExerciseSeeder.getExercises())
            }

            val userProfileDao = database.userProfileDao()
            // Provide a starter profile if empty
            userProfileDao.upsert(
                UserProfile(
                    id = 1,
                    weightKg = 75f,
                    heightCm = 178f,
                    targetWeightKg = 78f,
                    name = "Athlete",
                    lastUpdated = DateUtils.todayDateString()
                )
            )
        }
    }
}
