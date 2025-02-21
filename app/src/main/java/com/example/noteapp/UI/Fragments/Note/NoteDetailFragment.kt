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
            App.appDatabase?.noteDao()?.insert(NoteModel(etTitle,etDescription,savedDate,savedTime))
            findNavController().navigateUp()
        }
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

}