package com.example.newsapp.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.UI.database.ArticleDatabase
import com.example.newsapp.UI.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderfactory = NewsViewModelProviderfactory(newsRepository)

        viewModel = ViewModelProvider(this, viewModelProviderfactory).get(NewsViewModel::class.java)

        bottomnavigation.setupWithNavController(newsnavfragment.findNavController())
    }
}