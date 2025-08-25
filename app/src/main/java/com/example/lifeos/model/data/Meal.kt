package com.example.lifeos.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Meal_Table")
data class Meal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val calories: Int
)

