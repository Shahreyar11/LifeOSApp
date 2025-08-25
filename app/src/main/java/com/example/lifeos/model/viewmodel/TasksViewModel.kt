package com.example.lifeos.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeos.model.data.Tasks
import com.example.lifeos.model.local.TasksDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TasksViewModel(
    application: Application,
    private val userId: String,
    private val date: String)
    : AndroidViewModel(application) {

    private val dao = TasksDatabase.getDatabase(application).tasksDao()

    val tasks = dao.getTasks(userId, date)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(name: String) {
        viewModelScope.launch {
            dao.insert(Tasks(
                name = name,
                isDone = false,
                userId = userId,
                date = date
            ))
        }
    }

    fun toggleTask(task: Tasks) {
        viewModelScope.launch {
            dao.update(task.copy(isDone = !task.isDone))
        }
    }
}
