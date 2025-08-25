package com.example.lifeos.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_table")
data class Study_tasks(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val isDone: Boolean,
    val userId: String
)
