package com.example.noteapp.View.UI.interfaces

import com.example.noteapp.Model.Data.models.NoteModel

interface OnClickItem {

    fun onLongClick(noteModel: NoteModel)

    fun onCLick(noteModel: NoteModel)
}