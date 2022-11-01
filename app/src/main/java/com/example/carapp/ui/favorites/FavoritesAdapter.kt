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

    private var drinkList = arrayListOf<DrinkEntity>()
    private lateinit var mListener: onItemClickListener

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_drinks, parent, false)
        return ViewHolder(view, mListener, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drink = drinkList[position]
        holder.vinculate(drink, drinkList)
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    interface onItemClickListener {
        fun onItemClick(posicao: Int)
    }

    class ViewHolder(
        itemView: View,
        listener: onItemClickListener,
        context: Context
    ) : RecyclerView.ViewHolder(itemView) {

        val dao = DrinkDatabase.getInstance(context).getDrinkDao()
        val viewModel = FavoritesViewModel(dao)

        fun vinculate(drink: DrinkEntity, drinkList: ArrayList<DrinkEntity>) {
            val icon = itemView.findViewById<ImageView>(R.id.imageView)
            val name = itemView.findViewById<TextView>(R.id.text_home)
            val favoriteIcon = itemView.findViewById<ToggleButton>(R.id.favoriteToggle)
            Glide.with(itemView).load(drink.strThumb).transform(CenterCrop()).into(icon)
            name.text = drink.strDrink
            favoriteIcon.isChecked = true

            favoriteIcon.setOnClickListener {
                if (!favoriteIcon.isChecked) {
                    drinkList.remove(drink)
                    viewModel.remove(drink)

                } else {
                    drinkList.add((drink))
                    viewModel.save(drink)
                }
            }
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }


    }

    fun populateAdapter(newList: List<DrinkEntity>) {
        val oldPositions = drinkList.size
        val newPositions = newList.size
        drinkList.clear()
        drinkList.addAll(newList)
        notifyItemRangeInserted(oldPositions, newPositions)
    }
}