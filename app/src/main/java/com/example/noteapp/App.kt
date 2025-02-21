package com.example.noteapp

import android.app.Application
<<<<<<< HEAD
import android.content.Context
import androidx.room.Room
import com.example.noteapp.Data.db.AppDatabase
import com.example.noteapp.utils.PreferenceHelper

class App: Application() {

    companion object{
        var appDatabase:AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(this)
        getInstance()
    }

    private fun getInstance():AppDatabase? {
        if (appDatabase == null){
            appDatabase = applicationContext?.let {context: Context ->
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    name = "note_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
=======
import com.example.noteapp.utils.PreferenceHelper

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(this)
>>>>>>> origin/RoomHw5
    }
}