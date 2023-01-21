package com.example.newsapp.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.core.models.NewTableModel

@Database(entities = [NewTableModel::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {

    abstract fun getDao(): Dao

}
