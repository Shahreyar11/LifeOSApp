package com.example.lifeos.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lifeos.model.data.Tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert
    suspend fun insert(task: Tasks)

    @Update
    suspend fun update(task: Tasks)

    @Query("SELECT * FROM task_table WHERE userId = :userId AND date = :date")
    fun getTasks(userId: String, date: String): Flow<List<Tasks>>
}
