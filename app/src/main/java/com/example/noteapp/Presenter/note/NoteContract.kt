package com.example.noteapp.Presenter.note

import com.example.noteapp.Model.Data.models.NoteModel

interface NoteContract {
    interface View{
        fun showNotes(note:List<NoteModel>)
        fun showError(message:String)
        fun showToastNote (message:String)
    }

    interface Presenter{
        fun loadNotes()
        fun deleteNotes(note: NoteModel)
    }
}

