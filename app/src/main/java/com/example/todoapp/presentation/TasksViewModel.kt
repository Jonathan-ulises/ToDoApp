package com.example.todoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.todoapp.core.Result
import com.example.todoapp.domain.TaskRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class TasksViewModel(private val repo: TaskRepository): ViewModel() {
    fun fetchAllTask() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.getAllTask()))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

    fun saveTask(title: String, description: String, isFinished: Boolean) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.saveTask(title, description, isFinished)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}

class TaskViewModelFactory(private val repo: TaskRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TaskRepository::class.java).newInstance(repo)
    }
}