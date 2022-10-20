package com.example.carapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://parallelum.com.br/fipe/api/v1")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    }