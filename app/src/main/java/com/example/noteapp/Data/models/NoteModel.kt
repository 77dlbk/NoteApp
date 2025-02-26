package com.example.noteapp.Data.models

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val color: Int = Color.YELLOW
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
