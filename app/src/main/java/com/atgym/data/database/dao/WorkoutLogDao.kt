package com.atgym.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atgym.data.database.entity.WorkoutLog
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: WorkoutLog)

    @Query("SELECT * FROM workout_logs WHERE date = :date ORDER BY id")
    fun getLogsForDate(date: String): Flow<List<WorkoutLog>>

    @Query("SELECT * FROM workout_logs WHERE exerciseId = :exerciseId ORDER BY date DESC, setNumber")
    fun getLogsForExercise(exerciseId: Int): Flow<List<WorkoutLog>>

    @Query("SELECT DISTINCT date FROM workout_logs ORDER BY date DESC LIMIT :limit")
    fun getRecentDates(limit: Int = 30): Flow<List<String>>

    @Query("SELECT COUNT(DISTINCT date) FROM workout_logs")
    fun getTotalWorkoutDays(): Flow<Int>

    @Delete
    suspend fun delete(log: WorkoutLog)
}
