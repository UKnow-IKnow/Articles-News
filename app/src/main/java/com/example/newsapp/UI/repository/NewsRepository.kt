package com.example.newsapp.UI.repository

import com.example.newsapp.UI.api.RetrofitInstance
import com.example.newsapp.UI.database.ArticleDatabase

class NewsRepository(
    val db : ArticleDatabase
) {
    suspend fun getBreakingNews(counntrycode: String, pagenumber: Int) =
        RetrofitInstance.api.getBreakingNews(counntrycode,pagenumber)

    suspend fun searchForNews(searchQuery: String, pagenumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pagenumber)
}