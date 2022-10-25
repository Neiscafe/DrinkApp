package com.example.carapp.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

object RetroFit {

    private val retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val api: DrinkService by lazy{
        retrofit.create(DrinkService::class.java)

    }
}