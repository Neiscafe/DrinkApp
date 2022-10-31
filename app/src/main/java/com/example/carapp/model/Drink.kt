package com.example.carapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Drink(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int = 0,
    @SerializedName("strDrink") val strDrink: String = "strDrink",
    @SerializedName("strInstructions") val strInstructions: String = "strInstructions",
    @SerializedName("strThumb") val strThumb: String = "strThumb"

)
