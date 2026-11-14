package com.atgym.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atgym.data.database.entity.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises ORDER BY muscleGroup, name")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE muscleGroup = :group ORDER BY name")
    fun getByMuscleGroup(group: String): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE name LIKE '%' || :query || '%' OR muscleGroup LIKE '%' || :query || '%'")
    fun searchExercises(query: String): Flow<List<Exercise>>

    @Query("SELECT DISTINCT muscleGroup FROM exercises ORDER BY muscleGroup")
    fun getMuscleGroups(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exercises: List<Exercise>)

    @Query("SELECT COUNT(*) FROM exercises")
    suspend fun getCount(): Int
}
