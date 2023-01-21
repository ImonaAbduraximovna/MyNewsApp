package com.example.newsapp.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.core.cache.LocalStorage
import com.example.newsapp.core.models.Article
import com.example.newsapp.core.netWork.NewsRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val TAG = "MainViewModelTAG"

    private val newsApi = NewsRetrofit.getNewsClient()
    private val loc = LocalStorage()

    val topHeadlineLiveData = MutableLiveData<List<Article>>()
    val searchLiveData = MutableLiveData<List<Article>>()
    val errorLiveData = MutableLiveData<String>()

    fun loadTopHeadline(country: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = newsApi.loadTopHeadline(
                    country = country,
                    apiKey = "12eae44af1cd4420973ed82bbc97b4fc"
                )
                topHeadlineLiveData.postValue(response.articles)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }

    }

    private var isReadyResponse = true

    fun searchNews(q: String) {
        if (isReadyResponse) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    delay(2_000)
                    isReadyResponse = false
                    val response =
                        newsApi.searchNews(q = q, apiKey = "12eae44af1cd4420973ed82bbc97b4fc")
                    Log.d(TAG, "searchNews: ${response.totalResults}")
                    searchLiveData.postValue(response.articles)
                    isReadyResponse = true
                } catch (e: Exception) {
                    errorLiveData.postValue(e.message)
                    Log.d(TAG, "searchNews: ${e.message}")
                    isReadyResponse = true
                }
            }
        }
    }


    fun getCategory(category: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = newsApi.getCategory(
                    apiKey = "12eae44af1cd4420973ed82bbc97b4fc", category = category
                )
                topHeadlineLiveData.postValue(response.articles)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }

    }

}