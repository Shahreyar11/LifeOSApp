package com.example.lifeos.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeos.model.data.Study_tasks
import com.example.lifeos.model.local.StudyDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class StudyViewModel(application: Application, private val userId: String) : AndroidViewModel(application) {

    private val dao = StudyDatabase.getDatabase(application).StudyDao()

    val Study_tasks = dao.getAllTasks(userId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(name: String) {
        viewModelScope.launch {
            dao.insert(Study_tasks(name = name, isDone = false, userId = userId))
        }
    }

    fun toggleTask(task: Study_tasks) {
        viewModelScope.launch {
            dao.update(task.copy(isDone = !task.isDone))
        }
    }
}