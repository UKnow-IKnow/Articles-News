package com.example.newsapp.UI.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.UI.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {

    /**
     * or lateinit var viewModel : NewsViewModel
     * then in onViewCreated
     * viewModel  = ViewModelProvider(requireActivity).get(NewsViewModel::class.java)
     */

    private val viewModel by activityViewModels<NewsViewModel>()

    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
        wbview.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }


    }
}

