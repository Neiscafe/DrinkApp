package com.example.carapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drink(
    @SerializedName("id") val id: Int,
    @SerializedName("strDrink")val strDrink: String,
    @SerializedName("strInstructions")val strInstructions: String,
    @SerializedName("strDrinkThumb")val strDrinkThumb: String
) : Parcelable
