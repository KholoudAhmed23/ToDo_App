package com.example.todoapp.view

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.UserData

@Dao
interface  UserDao {
    @Query("select * from todo_table ")
    fun getAllUser():LiveData<List<UserData>>

    @Insert
    suspend fun insertUser(userData: UserData)

    @Update
    suspend fun updateUser(userData: UserData)

    @Delete
    suspend fun deleteUser(userData: UserData)
}