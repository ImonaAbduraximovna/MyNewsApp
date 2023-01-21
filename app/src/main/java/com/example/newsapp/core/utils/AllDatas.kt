package com.example.newsapp.core.utils

import com.example.newsapp.core.models.CountryModels


fun getCountryData():ArrayList<CountryModels>{
    val data = ArrayList<CountryModels>()

    data.add(CountryModels("Usa" , "us"))
    data.add(CountryModels("Russiya" , "ru"))
    data.add(CountryModels("Japan" , "jp"))

    return data
}