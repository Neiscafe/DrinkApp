package com.example.carapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.carapp.model.Drink
import com.example.carapp.model.DrinkEntity

@Dao
interface DrinkDao {

    @Insert
    suspend fun save(drink: DrinkEntity)
//
//    @Query("SELECT * FROM Drink")
//    suspend fun searchAll(): List<Drink>

}
