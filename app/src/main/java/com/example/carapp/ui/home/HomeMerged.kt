package com.example.carapp.ui.home

import com.example.carapp.model.Drink


sealed class HomeMerged {
        data class CocktailData(val drinkItems: List<Drink>) : HomeMerged()
    }
