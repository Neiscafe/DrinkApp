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
import com.example.carapp.R
import com.example.carapp.database.dao.DrinkDao
import com.example.carapp.databinding.ItemDrinksBinding
import com.example.carapp.model.Drink
import com.example.carapp.model.DrinkEntity

class HomeAdapter
    (
    private var drinks: List<DrinkEntity>,
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
                Glide.with(root).load(drink.strThumb).placeholder(circularProgressDrawable)
                    .centerCrop().into(imageView)
            }
            if (drink != null) {
                textHome.text = drink.strDrink

            }

            root.setOnClickListener {
                if (drink != null) {
                    listener.onItemClick(
                        drink.id,
                        drink.strDrink,
                        drink.strInstructions,
                        drink.strThumb,
                        "homeFragment"
                    )
                }
            }

        }
    }

    interface ListItemListener {
        fun onItemClick(
            id: Int,
            drinkName: String,
            drinkInstructions: String,
            drinkImage: String,
            fragmentName: String
        )
    }
}



