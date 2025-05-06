package com.billy.taskflow.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val Date: String,
    val time: String,
    val description: Double,
)

