package com.example.noteapp.UI.Adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.noteapp.UI.Fragments.onBoard.OnBoardPagerFragment
import com.example.noteapp.UI.Fragments.onBoard.OnBoardPagerFragment.Companion.ARG_ONBOARD_POSITION

class OnBoardAdapter(fragment: Fragment):FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int) = OnBoardPagerFragment().apply {
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION , position)
        }

    }


}