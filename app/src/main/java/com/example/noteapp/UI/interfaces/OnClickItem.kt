package com.example.noteapp.UI.interfaces

import com.example.noteapp.Data.models.NoteModel

interface OnClickItem {

    fun onLongClick(noteModel: NoteModel)

    fun onCLick(noteModel: NoteModel)
}