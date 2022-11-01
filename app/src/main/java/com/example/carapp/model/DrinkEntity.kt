package com.example.carapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teste")
class DrinkEntity (
    @PrimaryKey
    val id: Long
)
