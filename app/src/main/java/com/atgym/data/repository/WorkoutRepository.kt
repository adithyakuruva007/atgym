package com.atgym.data.repository

import com.atgym.data.database.dao.SetCompletionDao
import com.atgym.data.database.dao.WorkoutLogDao
import com.atgym.data.database.entity.SetCompletion
import com.atgym.data.database.entity.WorkoutLog
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(
    private val workoutLogDao: WorkoutLogDao,
    private val setCompletionDao: SetCompletionDao
) {
    suspend fun logSet(log: WorkoutLog) = workoutLogDao.insert(log)
    fun getLogsForDate(date: String): Flow<List<WorkoutLog>> = workoutLogDao.getLogsForDate(date)
    fun getLogsForExercise(exerciseId: Int): Flow<List<WorkoutLog>> = workoutLogDao.getLogsForExercise(exerciseId)
    fun getTotalWorkoutDays(): Flow<Int> = workoutLogDao.getTotalWorkoutDays()

    suspend fun toggleSetCompletion(dayIndex: Int, exerciseName: String, setNumber: Int, weekStart: String) {
        val existing = setCompletionDao.getSpecificCompletion(dayIndex, exerciseName, setNumber, weekStart)
        if (existing != null) {
            setCompletionDao.deleteSpecific(dayIndex, exerciseName, setNumber, weekStart)
        } else {
            setCompletionDao.insert(SetCompletion(dayIndex, exerciseName, setNumber, weekStart, true))
        }
    }

    fun getCompletionsForDay(dayIndex: Int, weekStart: String): Flow<List<SetCompletion>> =
        setCompletionDao.getCompletionsForDay(dayIndex, weekStart)

    fun getCompletionsForWeek(weekStart: String): Flow<List<SetCompletion>> =
        setCompletionDao.getCompletionsForWeek(weekStart)

    suspend fun clearDay(dayIndex: Int, weekStart: String) =
        setCompletionDao.clearDay(dayIndex, weekStart)

    fun getWeeklyCompletionCount(weekStart: String): Flow<Int> =
        setCompletionDao.getWeeklyCompletionCount(weekStart)
}
