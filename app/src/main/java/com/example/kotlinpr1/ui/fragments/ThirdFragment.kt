package com.example.kotlinpr1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kotlinpr1.databinding.FragmentThirdBinding
import com.example.kotlinpr1.ui.viewModel.QuizViewModel

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private val quizViewModel: QuizViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ClearDatabaseButton.setOnClickListener()
        {
            quizViewModel.deleteAll()
        }
    }
}