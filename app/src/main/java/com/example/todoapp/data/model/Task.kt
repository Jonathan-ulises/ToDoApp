package com.example.todoapp.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Task(
    val title: String = "",
    val description: String = "",
    val isFinished: Boolean = false,
    @ServerTimestamp var createdAt: Date? = null
)
