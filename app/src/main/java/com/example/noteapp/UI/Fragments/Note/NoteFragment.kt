package com.example.noteapp.UI.Fragments.Note

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.App
import com.example.noteapp.Data.models.NoteModel
import com.example.noteapp.R
import com.example.noteapp.UI.Adapters.NoteAdapter
import com.example.noteapp.UI.interfaces.OnClickItem


import com.example.noteapp.databinding.FragmentNoteBinding

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private var isGrid = false

    private val noteAdapter = NoteAdapter(onLongClick = this, onClick = this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListener()
        getData()

    }


    private fun initialize() {
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }

    }

    private fun setupListener() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
        binding.gridIcon.setOnClickListener {
            toggleLayoutManager()
        }
    }

    private fun toggleLayoutManager() {
        isGrid = !isGrid
        noteAdapter.isGridMode = isGrid // Обновляем состояние адаптера

        binding.rvNote.layoutManager = if (isGrid) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }

        binding.gridIcon.setImageResource(
            if (isGrid) R.drawable.ic_list else R.drawable.ic_grid
        )


        noteAdapter.notifyDataSetChanged()
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){listModel ->
            noteAdapter.submitList(listModel)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder){
            setTitle("Удалить заметку?")
            setPositiveButton("Удалить"){
                _,_ ->
                 App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Отмена"){dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onCLick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }


}
