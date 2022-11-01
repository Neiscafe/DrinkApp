package com.example.carapp.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carapp.database.dao.DrinkDao
import com.example.carapp.model.Drink
import com.example.carapp.model.DrinkEntity
import com.example.carapp.model.DrinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class FavoritesViewModel(val dao: DrinkDao) : ViewModel() {

    var favoritesListLiveData: LiveData<List<DrinkEntity>> = retrieveAll()

    private val repository: DrinkRepository = DrinkRepository(dao)

    init {
        favoritesListLiveData = repository.getFavorites
    }

    fun retrieveAll(): LiveData<List<DrinkEntity>> {
        return dao.retrieveAll()
    }

    fun remove(drink: DrinkEntity) {
        viewModelScope.launch{
            return@launch dao.remove(drink)
        }
    }

    fun save(drink: DrinkEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDrink(drink)
        }
    }

//    fun searchById(drinkEntity: DrinkEntity): DrinkEntity {
//        return dao.searhById(drinkEntity.id)
//    }

}