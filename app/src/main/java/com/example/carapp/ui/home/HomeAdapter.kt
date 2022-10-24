package com.example.carapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.carapp.R
import com.example.carapp.model.Drink

class HomeAdapter
    (private var drinks: MutableList<Drink>,
     private val onDrinkClick: (drink: Drink) -> Unit
) : RecyclerView.Adapter<HomeAdapter.DrinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_drinks, parent, false)
        return DrinkViewHolder(view)
    }

    override fun getItemCount(): Int = drinks.size

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.bind(drinks[position])
    }

    fun chamaDrinks(drinks: List<Drink>) {
        this.drinks.addAll(drinks)
        notifyItemRangeInserted(
            this.drinks.size,
            drinks.size - 1
        )
    }

    inner class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val thumb: ImageView = itemView.findViewById(R.id.ivCar_home)

        fun bind(drink: Drink) {
            Glide.with(itemView)
                .load("https://images/media/drink/vrwquq1478252802.jpg/preview${drink.strThumb}")
                .transform(CenterCrop())
                .into(thumb)

            itemView.setOnClickListener { onDrinkClick.invoke(drink) }
        }
    }


}



