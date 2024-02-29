package com.example.todoapp.domain

import com.example.todoapp.data.model.Task
import com.example.todoapp.data.remote.TaskDataSource

class TaskRepositoryImpl(private val dataSource: TaskDataSource): TaskRepository {
    override suspend fun saveTask(title: String, description: String, isFinished: Boolean) =
        dataSource.saveTask(title, description, isFinished)

    override suspend fun getAllTask(): List<Task> = dataSource.getAllTask()
}