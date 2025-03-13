package com.example.noteapp.Presenter.write

import com.example.noteapp.Model.Data.models.NoteModel

interface WriteNoteContract {
    interface View{
        fun showError(message:String)
        fun noteSaved()
        fun noteUpdated()

    }

    interface Presenter{
        fun saveNote(noteModel: NoteModel)
        fun updateNote(noteModel: NoteModel)
    }
}