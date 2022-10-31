package com.example.carapp.ui.home

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.carapp.R
import com.example.carapp.database.dao.DrinkDao
import com.example.carapp.databinding.ItemDrinksBinding
import com.example.carapp.model.Drink
import com.example.carapp.model.DrinkEntity

class HomeAdapter
    (private var drinks: List<DrinkEntity>?,
     private val listener: ListItemListener,
     private val dao: DrinkDao
) : RecyclerView.Adapter<HomeAdapter.DrinkViewHolder>() {

    private lateinit var context: Context
    private var viewModel = HomeViewModel()
    private var favoriteDrinks = mutableListOf<DrinkEntity>()

    inner class DrinkViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemDrinksBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_drinks, parent, false)
        return DrinkViewHolder(view)
    }

    override fun getItemCount() = drinks!!.size

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = drinks?.get(holder.adapterPosition)

        with(holder.binding.favoriteToggle) {
            setOnClickListener {
                if (this.isChecked) {
                    Log.i(TAG, "onBindViewHolder: O botao está checado")
                    drink?.let { drink ->
                        favoriteDrinks.add(drink)
                        Log.i(TAG, "onBindViewHolder: $favoriteDrinks")
                        viewModel.save(dao, drink)
                    }
                } else {
                    Log.i(TAG, "onBindViewHolder: O botao não está checado")
                }
            }
        }

        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        with(holder.binding) {
            if (drink != null) {
                    Glide.with(root).load(drink.strThumb).transform(CenterCrop()).into(imageView)
                    textHome.text = drink.strDrink
            }
            if (drink != null) {
                textHome.text = drink.strDrink
            }


            root.setOnClickListener{
                if (drink != null) {
                    listener.onItemClick(drink.id,
                        drink.strDrink,
                        drink.strInstructions,
                        drink.strThumb,
                        drink.strIngredient1,
                        drink.strMeasure1,
                        drink.strIngredient2,
                        drink.strMeasure2,
                        drink.strIngredient3,
                        drink.strMeasure3,
                        drink.strIngredient4,
                        drink.strMeasure4,
                        drink.strIngredient5,
                        drink.strMeasure5,
                        drink.strIngredient6,
                        drink.strMeasure6,
                        drink.strIngredient7,
                        drink.strMeasure7,
                        drink.strIngredient8,
                        drink.strMeasure8,
                        drink.strIngredient9,
                        drink.strMeasure9,
                        drink.strIngredient10,
                        drink.strMeasure10,
                        drink.strIngredient11,
                        drink.strMeasure11,
                        drink.strIngredient12,
                        drink.strMeasure12,
                        drink.strIngredient13,
                        drink.strMeasure13,
                        drink.strIngredient14,
                        drink.strMeasure14,
                        drink.strIngredient15,
                        drink.strMeasure15,
                        "homeFragment")
                }
            }
        }
    }

    interface ListItemListener {
        fun onItemClick(id: Int?,
                        drinkName: String?,
                        drinkInstructions: String?,
                        drinkImage: String?,
                        drinkIngredient1: String?,
                        drinkMeasure1: String?,
                        drinkIngredient2: String?,
                        drinkMeasure2: String?,
                        drinkIngredient3: String?,
                        drinkMeasure3: String?,
                        drinkIngredient4: String?,
                        drinkMeasure4: String?,
                        drinkIngredient5: String?,
                        drinkMeasure5: String?,
                        drinkIngredient6: String?,
                        drinkMeasure6: String?,
                        drinkIngredient7: String?,
                        drinkMeasure7: String?,
                        drinkIngredient8: String?,
                        drinkMeasure8: String?,
                        drinkIngredient9: String?,
                        drinkMeasure9: String?,
                        drinkIngredient10: String?,
                        drinkMeasure10: String?,
                        drinkIngredient11: String?,
                        drinkMeasure11: String?,
                        drinkIngredient12: String?,
                        drinkMeasure12: String?,
                        drinkIngredient13: String?,
                        drinkMeasure13: String?,
                        drinkIngredient14: String?,
                        drinkMeasure14: String?,
                        drinkIngredient15: String?,
                        drinkMeasure15: String?,
                        fragmentName: String)
    }
}



