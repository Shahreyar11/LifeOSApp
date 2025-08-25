package com.example.lifeos.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeos.model.data.Meal
import com.example.lifeos.model.data.Study_tasks
import com.example.lifeos.model.local.MealDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CalorieViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = MealDatabase.getDatabase(application).MealDao()

    val meals = dao.getAllMeals()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalCalories = dao.getTotalCalories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun addMeal(meal: Meal) {
        viewModelScope.launch {
            dao.insert(meal)
        }
    }
}


