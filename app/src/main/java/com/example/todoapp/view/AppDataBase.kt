package com.example.todoapp.view

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.model.UserData


@Database(entities = [UserData::class], version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun getDao():UserDao

    companion object{
        @Volatile
        private var instance:AppDataBase?=null

        fun detDataBase(context: Context):AppDataBase{
            return instance?: synchronized(this){
                val localInstance= Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,"todo_database"
                ).fallbackToDestructiveMigration().build()
                instance=localInstance
                localInstance
            }
        }
    }
}