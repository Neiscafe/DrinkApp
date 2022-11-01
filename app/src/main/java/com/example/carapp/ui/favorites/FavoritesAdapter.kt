package com.example.carapp.ui.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.carapp.R
import com.example.carapp.database.DrinkDatabase
import com.example.carapp.model.DrinkEntity

class FavoritesAdapter(val context: Context) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    val drinkList = arrayListOf<DrinkEntity>()
    private lateinit var mListener: onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_drinks, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drink = drinkList[position]
        holder.vinculate(drink)
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    interface onItemClickListener {
        fun onItemClick(posicao: Int)
        fun onFavoriteClick(posicao: Int, isChecked: Boolean)
    }

    inner class ViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        fun vinculate(drink: DrinkEntity) {
            val icon = itemView.findViewById<ImageView>(R.id.imageView)
            val name = itemView.findViewById<TextView>(R.id.text_home)
            Glide.with(itemView).load(drink.strThumb).transform(CenterCrop()).into(icon)
            name.text = drink.strDrink



            val favoriteIcon = itemView.findViewById<ToggleButton>(R.id.favouriteToggle)
            favoriteIcon.isChecked = true

            favoriteIcon.setOnClickListener {

                mListener.onFavoriteClick(bindingAdapterPosition, favoriteIcon.isChecked)

            }

            itemView.setOnClickListener{
                mListener.onItemClick(bindingAdapterPosition)
            }
        }
    }

    fun populateAdapter(newList: List<DrinkEntity>) {
        drinkList.clear()
        drinkList.addAll(newList)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
}