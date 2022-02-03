package com.example.newsapp.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.UI.models.NewsResponce
import com.example.newsapp.UI.repository.NewsRepository
import com.example.newsapp.UI.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel (
    val newsRepository: NewsRepository
    ) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponce>> = MutableLiveData()
    val breakingNewsPage = 1

    fun getBreakingNews(countrycode: String) = viewModelScope.launch {
        breakingNews.postValue((Resource.Loading()))
        val responce = newsRepository.getBreakingNews(countrycode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponce(responce))

    }

    private fun handleBreakingNewsResponce(responce: Response<NewsResponce>) : Resource<NewsResponce>{
        if(responce.isSuccessful){
            responce.body()?.let {
                    resultResponce ->
                return Resource.Success(resultResponce)
            }
        }
        return Resource.Error(responce.message())
    }

}