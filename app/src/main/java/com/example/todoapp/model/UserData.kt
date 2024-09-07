package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class UserData(
     @PrimaryKey(autoGenerate = true) val id: Int = 0, // Primary Key
     var Subject:String,
     var Task:String
)
