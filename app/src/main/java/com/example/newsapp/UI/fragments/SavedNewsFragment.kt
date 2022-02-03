package com.example.newsapp.UI.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newsapp.R
import com.example.newsapp.UI.MainActivity
import com.example.newsapp.UI.NewsViewModel

class SavedNewsFragment : Fragment(R.layout.fragment_saved){

    private val viewModel by activityViewModels<NewsViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}