package com.example.newsapp.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.UI.NewsViewModel
import com.example.newsapp.UI.adapters.NewsAdapter
import com.example.newsapp.UI.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.example.newsapp.UI.util.Resource
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.*


class SearchNewsFragment : Fragment(R.layout.fragment_search) {

    val viewModel by activityViewModels<NewsViewModel>()

    lateinit var newsAdapter: NewsAdapter

    val TAG = "SearchNewsFragment"




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        var job:Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable.let {
                    if (editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer {
                responce ->
            when(responce){
                is Resource.Success -> {
                    hideProgressBar()
                    responce.data?.let {
                            newsResponce ->
                        newsAdapter.differ.submitList(newsResponce.articles)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    responce.message?.let {
                            message ->
                        Log.e(TAG,"An error occured: $message")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        prgnavigationbar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        prgnavigationbar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        rvSearch.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}

