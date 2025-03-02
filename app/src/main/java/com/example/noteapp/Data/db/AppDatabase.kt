package com.example.noteapp.Data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.Data.db.daos.NoteDao
import com.example.noteapp.Data.models.NoteModel

@Database(entities = [NoteModel::class], version = 2)
abstract class AppDatabase:RoomDatabase(){
    abstract fun noteDao():NoteDao

}