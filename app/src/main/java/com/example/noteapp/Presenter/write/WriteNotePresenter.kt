package com.example.noteapp.Presenter.write

import com.example.noteapp.App
import com.example.noteapp.Model.Data.models.NoteModel

class WriteNotePresenter(
    private val view: WriteNoteContract.View
): WriteNoteContract.Presenter {
    override fun saveNote(noteModel: NoteModel) {
        view.noteSaved()
//        App.appDatabase?.noteDao()?.insert(NoteModel(etTitle,etDescription,savedDate,savedTime,selectedNoteColor))

    }

    override fun updateNote(noteModel: NoteModel) {
        App.appDatabase?.noteDao()?.updateNote(noteModel)
        view.noteUpdated()
    }
}