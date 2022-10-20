package com.example.carapp.model

import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("id") val id: Int,
    @SerializedName("strDrink") val strDrink: String,
    @SerializedName("strInstructions") val strInstructions: String,
    @SerializedName("strThumb") val strThumb: String
)
