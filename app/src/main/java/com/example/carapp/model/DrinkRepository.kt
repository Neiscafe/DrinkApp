package com.example.carapp.model

import androidx.lifecycle.LiveData
import com.example.carapp.database.dao.DrinkDao

class DrinkRepository (
    private val dao: DrinkDao
        ){

    suspend fun addDrink(drink: DrinkEntity){
        dao.save(drink)
    }

    val getFavorites: LiveData<List<DrinkEntity>> = dao.retrieveAll()
}