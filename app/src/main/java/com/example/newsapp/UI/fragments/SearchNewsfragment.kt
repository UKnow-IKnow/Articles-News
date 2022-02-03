package com.example.newsapp.UI.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.UI.MainActivity
import com.example.newsapp.UI.NewsViewModel

class SearchNewsFragment : Fragment(R.layout.fragment_search) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}