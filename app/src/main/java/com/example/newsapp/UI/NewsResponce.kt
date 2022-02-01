package com.example.newsapp.UI

data class NewsResponce(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)