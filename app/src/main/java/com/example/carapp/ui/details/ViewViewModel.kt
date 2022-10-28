package com.example.carapp.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.carapp.retrofit.RetroFit
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewViewModel (app: Application) : AndroidViewModel(app) {

    val json: MutableLiveData<String?>
        get() = _json

    val _json: MutableLiveData<String?> = MutableLiveData()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun getFullJson(searchQuery: Int?){
        viewModelScope.launch {
            RetroFit.api.getDrinkssJson(searchQuery).enqueue(object:
                Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    val stringResponse = response.body()?.string()
                    _json.postValue(stringResponse)
                }

            })
        }
    }
}
