package com.manohar.colaninfotechtask.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title: String,
    val description: String,
    val time: String,
    val date: String,
    val alarmType: String
)