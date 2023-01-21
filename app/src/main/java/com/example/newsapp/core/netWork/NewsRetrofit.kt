package com.example.newsapp.core.netWork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRetrofit {

    companion object {

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            }
        fun getNewsClient(): NewsApi {
            return getRetrofit().create(NewsApi::class.java)
        }

    }

}