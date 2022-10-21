package com.example.carapp.model

import com.google.gson.annotations.SerializedName

data class DrinkResponse (
    @SerializedName("drinks") val drinks: List<Drink>
)