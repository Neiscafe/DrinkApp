package com.example.carapp.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.carapp.model.Drink
import com.example.carapp.retrofit.RetroFit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel () : ViewModel() {

    val json: MutableLiveData<String>
        get() = _json

    val _json: MutableLiveData<String> = MutableLiveData()


    private val _drinks: MutableLiveData<List<Drink>> = MutableLiveData()
    val drinks: LiveData<List<Drink>>
        get() = _drinks

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun getCocktails(searchQuery: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _isLoading.postValue(true)

                val fetchedDrinks = RetroFit.api.getDrinksStr(searchQuery).drinks

                _drinks.postValue(fetchedDrinks)
                _isLoading.postValue(false)
            }
        }
    }

    fun fetchData(): MediatorLiveData<HomeMerged> {
        val liveDataMerger = MediatorLiveData<HomeMerged>()

        liveDataMerger.addSource(drinks) {
            if (it != null) {
                liveDataMerger.value = HomeMerged.CocktailData(it)
            }
        }
        return liveDataMerger
    }



}