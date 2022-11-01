package com.example.carapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carapp.R
import com.example.carapp.database.DrinkDatabase
import com.example.carapp.databinding.FragmentFavoritesBinding
import com.example.carapp.model.DrinkEntity
import com.example.carapp.ui.home.HomeAdapter
import com.example.carapp.ui.home.HomeFragmentDirections
import com.example.carapp.ui.home.ViewFragment

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var favoritesList = emptyList<DrinkEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val dao = DrinkDatabase.getInstance(requireContext()).getDrinkDao()

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.rvFavorites
        val tvNoFavorites = binding.tvNoFavorites
        tvNoFavorites.isVisible = false

        val adapter = FavoritesAdapter(requireContext())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val viewModel = FavoritesViewModel(dao)
        viewModel.favoritesListLiveData.observe(requireActivity()) { updatedList ->
            if (updatedList.isNotEmpty()) {
                favoritesList = emptyList()
                adapter.populateAdapter(updatedList)
            } else {
            }
        }

        adapter.setOnItemClickListener(object : FavoritesAdapter.onItemClickListener {
            override fun onItemClick(posicao: Int) {
                FavoritesFragmentDirections.actionNavigationNotificationsToViewFragment(adapter.drinkList[posicao])
            }

            override fun onFavoriteClick(posicao: Int, isChecked: Boolean) {
                if (isChecked) {
                    viewModel.save(adapter.drinkList[posicao])
                } else {
                    viewModel.remove(adapter.drinkList[posicao])
                }
            }
        })



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}