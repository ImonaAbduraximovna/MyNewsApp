package com.example.newsapp.core.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
class NewTableModel(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

