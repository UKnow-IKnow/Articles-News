package com.example.newsapp.UI.models

import com.example.newsapp.UI.models.Article

data class NewsResponce(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)