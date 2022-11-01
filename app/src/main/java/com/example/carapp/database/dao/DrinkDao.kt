package com.example.carapp.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.carapp.model.Drink
import com.example.carapp.model.DrinkEntity

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(drink: DrinkEntity)

    @Query("SELECT * FROM DrinkEntity")
    fun retrieveAll(): LiveData<List<DrinkEntity>>

    @Query("SELECT * FROM DrinkEntity")
    fun retrieve(): List<DrinkEntity>

    @Delete
    suspend fun remove(drink: DrinkEntity)

//    @Query("SELECT 1 FROM DrinkEntity WHERE id = :drinkId")
//    fun searhById(drinkId: Int): DrinkEntity


}
