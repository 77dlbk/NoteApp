package com.example.noteapp.UI.Fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardPagerBinding


class OnBoardPagerFragment : Fragment() {
    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
    private lateinit var binding: FragmentOnBoardPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()

    }

    private fun initialize() = with(binding) {
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0 ->{
                txtTitle.text = "Удобство"
                animatedView.setAnimation(R.raw.udobstvo)
                txtBody.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
            }
            1 ->{
                animatedView.setAnimation(R.raw.pencil)
                txtTitle.text = "Организация"
                txtBody.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
            }
            2 ->{
                animatedView.setAnimation(R.raw.christmas_tree)
                txtTitle.text = "Синхронизация"
                txtBody.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
            }
        }
    }


}