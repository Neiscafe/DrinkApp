package com.example.carapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drink(
    val id: Int,
    val strDrink: String,
    val strInstructions: String,
    val strThumb: String
) : Parcelable
