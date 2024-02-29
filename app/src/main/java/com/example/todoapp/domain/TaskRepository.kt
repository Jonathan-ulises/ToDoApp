package com.example.todoapp.domain

import com.example.todoapp.data.model.Task

interface TaskRepository {
    suspend fun saveTask(title: String, description: String, isFinished: Boolean)

    suspend fun getAllTask(): List<Task>
}