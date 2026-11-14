package com.atgym.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.atgym.data.database.entity.CardioLog
import kotlinx.coroutines.flow.Flow

@Dao
interface CardioLogDao {
    @Insert
    suspend fun insert(log: CardioLog)

    @Query("SELECT * FROM cardio_logs ORDER BY date DESC")
    fun getAllLogs(): Flow<List<CardioLog>>

    @Query("SELECT * FROM cardio_logs WHERE date = :date")
    fun getLogsForDate(date: String): Flow<List<CardioLog>>

    @Query("SELECT SUM(durationMinutes) FROM cardio_logs WHERE date >= :startDate")
    fun getTotalMinutesSince(startDate: String): Flow<Int?>

    @Query("SELECT SUM(caloriesBurned) FROM cardio_logs WHERE date >= :startDate")
    fun getTotalCaloriesSince(startDate: String): Flow<Int?>

    @Delete
    suspend fun delete(log: CardioLog)
}
