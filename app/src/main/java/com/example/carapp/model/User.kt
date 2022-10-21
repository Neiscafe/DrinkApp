package com.example.carapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    private val name: String,
    private val user: String,
    private val password: String,
    @PrimaryKey(autoGenerate = true)
    private val id: Int = 0
) {



}