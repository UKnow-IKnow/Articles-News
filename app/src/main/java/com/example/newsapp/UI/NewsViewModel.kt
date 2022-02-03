package com.example.newsapp.UI

import androidx.lifecycle.ViewModel
import com.example.newsapp.UI.repository.NewsRepository

class NewsViewModel (
    val newsRepository: NewsRepository
        ) : ViewModel() {
}