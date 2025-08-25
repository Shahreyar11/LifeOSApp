package com.example.lifeos.model.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TasksViewModelFactory(
    private val application: Application,
    private val userId: String,
    private val date: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            return TasksViewModel(application, userId, date) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
