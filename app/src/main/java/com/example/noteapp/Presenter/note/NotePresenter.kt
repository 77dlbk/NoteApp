package com.example.noteapp.Presenter.note

import com.example.noteapp.App
import com.example.noteapp.Model.Data.models.NoteModel

class NotePresenter(
    private val view: NoteContract.View
): NoteContract.Presenter {
    override fun loadNotes() {
        App.appDatabase?.noteDao()?.getAll()?.observeForever(){ listModel ->
            view.showNotes(listModel)
            view.showToastNote("Note Loaded")
    }
    }

    override fun deleteNotes(note: NoteModel) {
        App.appDatabase?.noteDao()?.deleteNote(note)
        view.showToastNote("Note Deleted ")
    }
}