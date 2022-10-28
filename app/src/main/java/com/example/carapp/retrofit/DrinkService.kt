package com.example.carapp.retrofit

import com.example.carapp.model.DrinkResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkService {

    @GET("search.php")
    suspend fun getDrinksStr(
        @Query("s") searchQuery: String
    ): DrinkResponse

    @GET("lookup.php")
    fun getDrinkssJson(
        @Query("i") searchQuery: Int?
    ): Call<ResponseBody>

    @GET("lookup.php")
     suspend fun getDrinkById(
        @Query("i") i: Int?
    ): DrinkResponse

    @GET("search.php")
     suspend fun getDrinks(
        @Query("s") s: String
    ): DrinkResponse

}


