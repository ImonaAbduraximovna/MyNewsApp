package com.example.newsapp.core.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.core.models.NewTableModel

@Dao
interface Dao {


    @Query("SELECT * FROM news")
    fun getAllData(): List<NewTableModel>

    @Query("DELETE FROM news WHERE urlToImage = (:urlImage)")
    fun deleteData(urlImage: String)

    @Query("SELECT urlToImage FROM news")
    fun getImages(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: NewTableModel)


}