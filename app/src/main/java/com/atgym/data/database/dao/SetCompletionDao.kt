package com.atgym.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atgym.data.database.entity.SetCompletion
import kotlinx.coroutines.flow.Flow

@Dao
interface SetCompletionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(completion: SetCompletion)

    @Query("SELECT * FROM set_completions WHERE dayIndex = :dayIndex AND weekStart = :weekStart")
    fun getCompletionsForDay(dayIndex: Int, weekStart: String): Flow<List<SetCompletion>>

    @Query("SELECT * FROM set_completions WHERE weekStart = :weekStart")
    fun getCompletionsForWeek(weekStart: String): Flow<List<SetCompletion>>

    @Query("SELECT * FROM set_completions WHERE dayIndex = :dayIndex AND exerciseName = :exerciseName AND setNumber = :setNumber AND weekStart = :weekStart LIMIT 1")
    suspend fun getSpecificCompletion(dayIndex: Int, exerciseName: String, setNumber: Int, weekStart: String): SetCompletion?

    @Query("DELETE FROM set_completions WHERE dayIndex = :dayIndex AND exerciseName = :exerciseName AND setNumber = :setNumber AND weekStart = :weekStart")
    suspend fun deleteSpecific(dayIndex: Int, exerciseName: String, setNumber: Int, weekStart: String)

    @Query("DELETE FROM set_completions WHERE dayIndex = :dayIndex AND weekStart = :weekStart")
    suspend fun clearDay(dayIndex: Int, weekStart: String)

    @Delete
    suspend fun delete(completion: SetCompletion)

    @Query("SELECT COUNT(*) FROM set_completions WHERE weekStart = :weekStart")
    fun getWeeklyCompletionCount(weekStart: String): Flow<Int>
}
