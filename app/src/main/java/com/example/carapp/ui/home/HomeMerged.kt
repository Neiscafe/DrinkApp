package com.example.carapp.ui.home

import com.example.carapp.model.DrinkEntity


sealed class HomeMerged {
        data class CocktailData(val drinkItems: List<DrinkEntity>) : HomeMerged()
    }
