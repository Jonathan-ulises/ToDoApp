package com.example.todoapp.data.remote

import com.example.todoapp.core.Constans
import com.example.todoapp.data.model.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class TaskDataSource {
    suspend fun saveTask(title: String, description: String, isFinished: Boolean) {
        FirebaseFirestore.getInstance().collection(Constans.COLLECTION_TASK)
            .add(
                Task(
                    title,
                    description,
                    isFinished
                )
            )
    }

    suspend fun getAllTask(): List<Task> {
        val taskList = mutableListOf<Task>()
        val querySnapshot = FirebaseFirestore.getInstance().collection(Constans.COLLECTION_TASK)
            .orderBy("createdAt", Query.Direction.DESCENDING).get().await()
        for(task in querySnapshot.documents) {
            task.toObject(Task::class.java)?.let { fbTask ->
                fbTask.apply {
                    createdAt = task.getTimestamp(
                        "createdAt",
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )?.toDate()
                }
                taskList.add(fbTask)
            }
        }

        return taskList
    }
}