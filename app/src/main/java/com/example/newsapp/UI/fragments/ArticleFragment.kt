package com.example.newsapp.UI.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newsapp.R
import com.example.newsapp.UI.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    /**
     * or lateinit var viewModel : NewsViewModel
     * then in onViewCreated
     * viewModel  = ViewModelProvider(requireActivity).get(NewsViewModel::class.java)
     */

    private val viewModel by activityViewModels<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}

