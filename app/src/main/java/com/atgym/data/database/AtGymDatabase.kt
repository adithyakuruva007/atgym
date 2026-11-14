package com.atgym.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atgym.data.database.dao.CardioLogDao
import com.atgym.data.database.dao.ExerciseDao
import com.atgym.data.database.dao.SetCompletionDao
import com.atgym.data.database.dao.UserProfileDao
import com.atgym.data.database.dao.WorkoutLogDao
import com.atgym.data.database.entity.CardioLog
import com.atgym.data.database.entity.Exercise
import com.atgym.data.database.entity.SetCompletion
import com.atgym.data.database.entity.UserProfile
import com.atgym.data.database.entity.WorkoutLog

@Database(
    entities = [Exercise::class, WorkoutLog::class, CardioLog::class, UserProfile::class, SetCompletion::class],
    version = 1,
    exportSchema = false
)
abstract class AtGymDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutLogDao(): WorkoutLogDao
    abstract fun cardioLogDao(): CardioLogDao
    abstract fun userProfileDao(): UserProfileDao
    abstract fun setCompletionDao(): SetCompletionDao

    companion object {
        @Volatile
        private var INSTANCE: AtGymDatabase? = null

        fun getDatabase(context: Context): AtGymDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AtGymDatabase::class.java,
                    "atgym_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
