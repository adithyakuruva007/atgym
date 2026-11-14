package com.atgym.data.repository

import com.atgym.data.database.dao.ExerciseDao
import com.atgym.data.database.entity.Exercise
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    val allExercises: Flow<List<Exercise>> = exerciseDao.getAllExercises()
    fun getByMuscleGroup(group: String): Flow<List<Exercise>> = exerciseDao.getByMuscleGroup(group)
    fun searchExercises(query: String): Flow<List<Exercise>> = exerciseDao.searchExercises(query)
    val muscleGroups: Flow<List<String>> = exerciseDao.getMuscleGroups()
    suspend fun insertAll(exercises: List<Exercise>) = exerciseDao.insertAll(exercises)
    suspend fun getCount(): Int = exerciseDao.getCount()
}
