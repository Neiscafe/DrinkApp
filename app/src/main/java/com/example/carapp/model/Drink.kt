package com.example.carapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Drink(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("strDrink") val strDrink: String,
    @SerializedName("strInstructions") val strInstructions: String,
    @SerializedName("strThumb") val strThumb: String
)
