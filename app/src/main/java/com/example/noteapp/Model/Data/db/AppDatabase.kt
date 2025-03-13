package com.example.noteapp.Model.Data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.Model.Data.db.daos.NoteDao
import com.example.noteapp.Model.Data.models.NoteModel

@Database(entities = [NoteModel::class], version = 2)
abstract class AppDatabase:RoomDatabase(){
    abstract fun noteDao(): NoteDao

}