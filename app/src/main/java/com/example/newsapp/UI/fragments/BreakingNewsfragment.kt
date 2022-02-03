package com.example.newsapp.UI.fragments

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.UI.MainActivity
import com.example.newsapp.UI.NewsViewModel
import com.example.newsapp.UI.adapters.NewsAdapter

class BreakingNewsfragment: Fragment(R.layout.fragment_breakingnews) {

    lateinit var  viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}