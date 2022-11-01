package com.example.carapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.carapp.R
import com.example.carapp.database.DrinkDatabase
import com.example.carapp.databinding.ItemDrinksBinding
import com.example.carapp.model.Drink
import com.example.carapp.model.DrinkEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeAdapter
    (
    private var drinks: List<DrinkEntity>?,
    private val listener: ListItemListener,
) : RecyclerView.Adapter<HomeAdapter.DrinkViewHolder>() {

    private lateinit var context: Context

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

        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        val toggle = holder.itemView.findViewById<ToggleButton>(R.id.favouriteToggle)

        toggle.isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val lista = DrinkDatabase.getInstance(context).getDrinkDao().retrieve()
            if (lista.isNotEmpty()) {
                for (favorites in lista) {
                    if (favorites.strDrink == drinks?.get(holder.absoluteAdapterPosition)?.strDrink) {
                        CoroutineScope(Dispatchers.Main).launch {
                            toggle.isChecked = true
                        }
                        break
                    }
                }
            }
        }


        toggle.setOnCheckedChangeListener { compoundButton, b ->
            if (drink != null) {
                listener.onFavoriteClick(drink, b)
            }
        }

        with(holder.binding) {
            if (drink != null) {
                Glide.with(root).load(drink.strThumb).transform(CenterCrop()).into(imageView)
                textHome.text = drink.strDrink
            }

            root.setOnClickListener {
                if (drink != null) {
                    listener.onItemClick(drink)
                }
            }
        }
    }

    interface ListItemListener {
        fun onItemClick(
            drink: DrinkEntity
        )

        fun onFavoriteClick(
            drink: DrinkEntity,
            isChecked: Boolean
        )
    }
}



