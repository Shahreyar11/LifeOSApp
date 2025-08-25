package com.example.lifeos.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lifeos.model.data.Meal
import com.example.lifeos.model.data.Tasks

@Database(entities = [Meal :: class], version = 1)
abstract class MealDatabase : RoomDatabase() {
    abstract fun MealDao(): MealDao

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun getDatabase(context: Context): MealDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "meal_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}