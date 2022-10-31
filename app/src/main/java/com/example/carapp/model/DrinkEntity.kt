package com.example.carapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrinkEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "str_drink") val strDrink: String,
    @ColumnInfo(name = "str_instructions") val strInstructions: String,
    @ColumnInfo(name = "str_thumb") val strThumb: String)