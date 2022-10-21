package com.example.carapp.retrofit

import com.example.carapp.model.DrinkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkService {

    @GET("lookup.php")
     fun getDrinkById(
        @Query("i") i: Int?
    ): Call<DrinkResponse>

    @GET("search.php")
     fun getDrinks(
        @Query("s") s: String
    ): Call<DrinkResponse>

}


