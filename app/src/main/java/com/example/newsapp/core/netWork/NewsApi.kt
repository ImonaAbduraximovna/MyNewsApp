package com.example.newsapp.core.netWork

import retrofit2.http.GET
import retrofit2.http.Query
import uz.azamat.myapplication.core.models.TopHeadLine.TopHeadLine

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun loadTopHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,

        ): TopHeadLine

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String,
        @Query("sortBy") sortBy: String = "popularity",
    ): TopHeadLine

    @GET("v2/top-headlines")
    suspend fun getCategory(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String,
    ): TopHeadLine


}