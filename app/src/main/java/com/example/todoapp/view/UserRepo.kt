package com.example.todoapp.view

import androidx.lifecycle.LiveData
import com.example.todoapp.model.UserData

class UserRepo(private val userDao: UserDao) {

    val allUser: LiveData<List<UserData>> = userDao.getAllUser()
    suspend fun insertUser(userData: UserData){
        userDao.insertUser(userData)
    }
    suspend fun updateUser(userData: UserData){
        userDao.updateUser(userData)
    }
    suspend fun deleteUser(userData: UserData){
        userDao.deleteUser(userData)
    }
}