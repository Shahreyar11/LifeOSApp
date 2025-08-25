package com.example.lifeos.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lifeos.model.data.Study_tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyDao {

    @Insert
    suspend fun insert(task: Study_tasks)

    @Update
    suspend fun update(task: Study_tasks)

    @Query("SELECT * FROM study_table WHERE userId = :userId")
    fun getAllTasks(userId: String): Flow<List<Study_tasks>>
}