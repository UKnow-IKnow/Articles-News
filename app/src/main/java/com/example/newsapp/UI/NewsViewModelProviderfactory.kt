package com.example.newsapp.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.UI.repository.NewsRepository

class NewsViewModelProviderfactory (
    val newsRepository: NewsRepository
        ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}