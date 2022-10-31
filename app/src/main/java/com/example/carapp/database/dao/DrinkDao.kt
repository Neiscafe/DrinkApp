package com.example.carapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.carapp.model.Drink

@Dao
interface DrinkDao {

    @Insert
    suspend fun save(drink: Drink)
//
//    @Query("SELECT * FROM Drink")
//    suspend fun searchAll(): List<Drink>

}
