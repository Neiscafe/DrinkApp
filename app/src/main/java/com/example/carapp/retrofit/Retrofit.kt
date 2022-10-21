package com.example.carapp.retrofit

import com.example.carapp.model.Drink
import com.example.carapp.model.DrinkResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {


    private val api: DrinkService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(DrinkService::class.java)
    }

    fun getDrinkById(
        i: Int, onSuccess: (drinks: List<Drink>) -> Unit,
        onError: () -> Unit
    ) {
        api.getDrinkById(i = i)
            .enqueue(object : Callback<DrinkResponse> {
                override fun onResponse(
                    call: Call<DrinkResponse>,
                    response: Response<DrinkResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.drinks)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<DrinkResponse>, t: Throwable) {
                    onError.invoke()
                }
            })

    }

    }