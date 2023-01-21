package com.example.newsapp.core.app

import android.app.Application
import androidx.room.Room
import com.example.newsapp.core.room.NewsDataBase
import com.yariksoffice.lingver.Lingver

class App : Application() {

    companion object {

        var instance: Application? = null
            private set

        var db: NewsDataBase? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        db = Room.databaseBuilder(this, NewsDataBase::class.java, "data-base")
            .allowMainThreadQueries().build()

        Lingver.init(this,"en")
    }


}