package com.example.noteapp.UI.Fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.UI.Adapters.OnBoardAdapter
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.utils.PreferenceHelper


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        initialize()
    }

    private fun initialize() {
        binding.viewPager.adapter = OnBoardAdapter(this)
        binding.dotsIndicator.attachTo(binding.viewPager)

    }
    private fun setupListener()  = with(binding.viewPager)
    {
       registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
           override fun onPageSelected(position: Int) = with(binding) {
               super.onPageSelected(position)
               if (position==2){
                   txtSkip.visibility = View.GONE
               }
               else{
                   txtSkip.visibility = View.VISIBLE
                   txtSkip.setOnClickListener {
                       setCurrentItem(currentItem + 2, true)
                   }
               }
               if (position ==2){
                   btnStart.visibility = View.VISIBLE
               }
               else {
                   btnStart.visibility = View.GONE
               }
               btnStart.setOnClickListener {
                   PreferenceHelper.onBoardShown = true // Устанавливаем флаг
                   findNavController().navigate(R.id.authFragment) // Переход в NoteFragment
               }

           }
       })


    }



}