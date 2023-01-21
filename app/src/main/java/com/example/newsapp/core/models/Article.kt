package com.example.newsapp.core.models


import com.google.gson.annotations.SerializedName
import uz.azamat.myapplication.core.models.TopHeadLine.Source

data class Article(
    @SerializedName("source")
    val source: Source,
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("content")
    val content: String
)