package com.example.carapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.example.carapp.R
import com.example.carapp.databinding.ItemDrinksBinding
import com.example.carapp.model.Drink

class HomeAdapter
    (private var drinks:List<Drink>,
     private val listener: ListItemListener
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

        with(holder.binding) {
            if (drink != null) {
                Glide.with(root).load(drink.strThumb).transform(CenterCrop()).into(imageView)
            }
            if (drink != null) {
                textHome.text = drink.strDrink

            }

            root.setOnClickListener{
                if (drink != null) {
                    listener.onItemClick(drink.id, drink.strDrink, drink.strInstructions, drink.strThumb, "homeFragment")
                }
            }

        }
    }

    interface ListItemListener {
        fun onItemClick(id: Int, drinkName: String, drinkInstructions: String, drinkImage: String, fragmentName: String)
    }
}



