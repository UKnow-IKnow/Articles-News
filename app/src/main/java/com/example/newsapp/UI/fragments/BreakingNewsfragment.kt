package com.example.newsapp.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.UI.MainActivity
import com.example.newsapp.UI.NewsViewModel
import com.example.newsapp.UI.adapters.NewsAdapter
import com.example.newsapp.UI.util.Resource
import kotlinx.android.synthetic.main.fragment_breakingnews.*

class BreakingNewsfragment: Fragment(R.layout.fragment_breakingnews) {

    lateinit var  viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsfragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
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
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}