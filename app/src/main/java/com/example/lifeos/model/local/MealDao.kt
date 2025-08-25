package com.example.lifeos.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lifeos.model.data.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(meal: Meal)

    @Query("SELECT * FROM Meal_Table ORDER BY id DESC")
    fun getAllMeals(): Flow<List<Meal>>

    @Query("SELECT SUM(calories) FROM Meal_Table")
    fun getTotalCalories(): Flow<Int>
}

