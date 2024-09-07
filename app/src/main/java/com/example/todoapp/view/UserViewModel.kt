package com.example.todoapp.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.model.UserData
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application){

    private val userRepo:UserRepo
    val allUser: LiveData<List<UserData>>
    init {
        val userDao = AppDataBase.detDataBase(application).getDao()
        userRepo = UserRepo(userDao)
        allUser = userRepo.allUser
    }
    fun insert(userData: UserData)=viewModelScope.launch {
        userRepo.insertUser(userData)
    }
    fun update(userData: UserData)=viewModelScope.launch {
        userRepo.updateUser(userData)
    }
    fun delete(userData: UserData)=viewModelScope.launch {
        userRepo.deleteUser(userData)
    }
}