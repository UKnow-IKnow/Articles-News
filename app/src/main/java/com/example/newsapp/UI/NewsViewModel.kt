package com.example.newsapp.UI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.UI.database.ArticleDatabase
import com.example.newsapp.UI.models.Article
import com.example.newsapp.UI.models.NewsResponce
import com.example.newsapp.UI.repository.NewsRepository
import com.example.newsapp.UI.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    application: Application
) : AndroidViewModel(application) {


    private val newsRepository = NewsRepository(ArticleDatabase(context = application))

    val breakingNews: MutableLiveData<Resource<NewsResponce>> = MutableLiveData()
    val breakingNewsPage = 1

    val searchNews: MutableLiveData<Resource<NewsResponce>> = MutableLiveData()
    val searchNewsPage = 1

    init {
        getBreakingNews("IND")
    }

    fun getBreakingNews(countrycode: String) = viewModelScope.launch {
        breakingNews.postValue((Resource.Loading()))
        val responce = newsRepository.getBreakingNews(countrycode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponce(responce))

    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchForNews(searchQuery,searchNewsPage)
        searchNews.postValue((handleSearchNewsResponce(response)))
    }

    private fun handleBreakingNewsResponce(responce: Response<NewsResponce>) : Resource<NewsResponce>{
        if(responce.isSuccessful){
            responce.body()?.let { resultResponce ->
                return Resource.Success(resultResponce)
            }
        }
        return Resource.Error(responce.message())
    }

    private fun handleSearchNewsResponce(responce: Response<NewsResponce>) : Resource<NewsResponce>{
        if(responce.isSuccessful){
            responce.body()?.let { resultResponce ->
                return Resource.Success(resultResponce)
            }
        }
        return Resource.Error(responce.message())
    }

    fun savedArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

}