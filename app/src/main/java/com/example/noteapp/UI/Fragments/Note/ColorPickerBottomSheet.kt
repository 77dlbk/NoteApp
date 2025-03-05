package com.example.noteapp.UI.Fragments.Note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.noteapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ColorPickerBottomSheet(private val onColorSelected: (Int) -> Unit) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottom_sheet_color_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colors = listOf(
            R.color.colorYellow, R.color.colorPurple, R.color.colorPink,
            R.color.colorRed, R.color.colorGreen, R.color.colorBlue
        )

        val colorButtons = listOf(
            view.findViewById<View>(R.id.color1),
            view.findViewById<View>(R.id.color2),
            view.findViewById<View>(R.id.color3),
            view.findViewById<View>(R.id.color4),
            view.findViewById<View>(R.id.color5),
            view.findViewById<View>(R.id.color6)
        )

        colorButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                onColorSelected(ContextCompat.getColor(requireContext(), colors[index]))
                dismiss()
            }
        }
    }
}
