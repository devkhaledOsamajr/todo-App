package com.example.todoapp.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date

@Entity(tableName = "todo")
data class Task(
    val title: String? = null,
    val description: String? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val date: Date? = null,
    var isDone: Boolean = false,
    var time: String? = null
)
