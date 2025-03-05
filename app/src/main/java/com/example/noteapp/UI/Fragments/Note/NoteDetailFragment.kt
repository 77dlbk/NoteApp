package com.example.noteapp.UI.Fragments.Note

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.Data.models.NoteModel
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import java.util.Calendar


class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var noteId:Int = -1
    private var selectedNoteColor: Int = Color.YELLOW
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCurrentDateTime()
        setupListener()
        updateNote()
    }

    private fun updateNote() {
        arguments?.let {args ->
            noteId = args.getInt("noteId", -1)
        }
        if (noteId != -1){
            val id =App.appDatabase?.noteDao()?.getById(noteId)
            id?.let {model->
                binding.txtTitle.setText(model.title)
                binding.txtDescription.setText(model.description)
                binding.txtDate.text = model.date
                binding.txtTime.text = model.time
                selectedNoteColor = model.color // Загружаем цвет заметки
                binding.btnColorPicker.setBackgroundColor(selectedNoteColor) // Меняем цвет кнопки
            }
        }
    }

    private fun setCurrentDateTime()  = with(binding){
        val calendar = Calendar.getInstance()

        val date = DateFormat.getDateFormat(requireContext()).format(calendar.time)
        val time = DateFormat.getTimeFormat(requireContext()).format(calendar.time)

        txtDate.text = date
        txtTime.text = time

    }

    @SuppressLint("ResourceAsColor")
    private fun setupListener() = with(binding) {
        btnReady.isEnabled = false
        btnReady.setTextColor(R.color.gray)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val isTitleNotEmpty = txtTitle.text.toString().isNotEmpty()
                val isDescriptionNotEmpty = txtDescription.text.toString().isNotEmpty()

                if (isTitleNotEmpty && isDescriptionNotEmpty) {
                    btnReady.isEnabled = true
                    btnReady.setTextColor(Color.parseColor("#D88B02")) // Оранжевый цвет
                } else {
                    btnReady.isEnabled = false
                    btnReady.setTextColor(R.color.gray) // Серый цвет
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        txtTitle.addTextChangedListener(textWatcher)
        txtDescription.addTextChangedListener(textWatcher)

        btnReady.setOnClickListener {
            val etTitle:String = txtTitle.text.toString()
            val etDescription = txtDescription.text.toString()
            val savedDate = txtDate.text.toString()
            val savedTime = txtTime.text.toString()
            if (noteId !=-1){
                val updateNote = NoteModel(etTitle,etDescription,savedDate,savedTime,selectedNoteColor)
                updateNote.id = noteId
                App.appDatabase?.noteDao()?.updateNote(updateNote)
            }else{
                App.appDatabase?.noteDao()?.insert(NoteModel(etTitle,etDescription,savedDate,savedTime,selectedNoteColor))
            }
            findNavController().navigateUp()
        }
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        btnColorPicker.setOnClickListener {
            val colorPicker = ColorPickerBottomSheet { selectedColor ->
                selectedNoteColor = selectedColor // Обновляем цвет заметки
                btnColorPicker.setBackgroundColor(selectedNoteColor) // Только для визуального отображения выбора
            }
            colorPicker.show(parentFragmentManager, "ColorPicker")
        }

    }

}