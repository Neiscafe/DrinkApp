package com.example.carapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carapp.model.Drink
import com.example.carapp.retrofit.RetroFit

class HomeViewModel : ViewModel() {
    private val _home = MutableLiveData<List<Drink>>()
    private val _error = MutableLiveData<Boolean>()

    val error: LiveData<Boolean>
        get() = _error


  /*  init {
        getDrinkById()
    }*/

    val home: LiveData<List<Drink>>
        get() = _home

    private fun onError() {
        _error.value = true
    }

    private fun Drinks(drinks: List<Drink>) {
        _home.value = drinks
    }

   /* fun getDrinkById(i: Int ) {
        RetroFit.getDrinkById(
            i,
            ::Drinks,
            ::onError
        )
    }*/

}