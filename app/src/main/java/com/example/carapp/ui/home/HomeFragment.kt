package com.example.carapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carapp.databinding.FragmentHomeBinding
import com.example.carapp.model.Drink

class HomeFragment : Fragment(),
    HomeAdapter.ListItemListener {
    private lateinit var viewModel: HomeViewModel
    private lateinit var searchQuery: String
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private lateinit var spinner: ProgressBar

    private val args: HomeFragmentArgs by navArgs()

    var drinkItems: List<Drink>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchQuery = args.searchQuery;

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        spinner = binding.progressBar1

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val liveData = viewModel.fetchData()
        viewModel.getCocktails(searchQuery)


        with(binding.homeRecycler) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
        }

        liveData.observe(viewLifecycleOwner
        ) { it ->
            when (it) {
                is HomeMerged.CocktailData -> drinkItems = it.drinkItems
            }

            if (drinkItems?.isNotEmpty() == true) {

                adapter =
                    HomeAdapter(drinkItems!!, this@HomeFragment)
                binding.homeRecycler.adapter = adapter
                binding.homeRecycler.layoutManager = LinearLayoutManager(activity)

            } else {
                binding.noDrinksFound.visibility = View.VISIBLE
            }

            if (it == null) {
                spinner.visibility = View.VISIBLE;
            } else {
                spinner.visibility = View.GONE;
            }
        }
        return binding.root
    }

    override fun onItemClick(
        id: Int?,
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
        fragmentName: String
    ) {
        val action = HomeFragmentDirections.actionViewDrink(
            id?:0,
            drinkName?:"",
            drinkInstructions?:"",
            drinkImage?:"",
            drinkIngredient1?:"",
            drinkMeasure1?:"",
            drinkIngredient2?:"",
            drinkMeasure2?:"",
            drinkIngredient3?:"",
            drinkMeasure3?:"",
            drinkIngredient4?:"",
            drinkMeasure4?:"",
            drinkIngredient5?:"",
            drinkMeasure5?:"",
            drinkIngredient6?:"",
            drinkMeasure6?:"",
            drinkIngredient7?:"",
            drinkMeasure7?:"",
            drinkIngredient8?:"",
            drinkMeasure8?:"",
            drinkIngredient9?:"",
            drinkMeasure9?:"",
            drinkIngredient10?:"",
            drinkMeasure10?:"",
            drinkIngredient11?:"",
            drinkMeasure11?:"",
            drinkIngredient12?:"",
            drinkMeasure12?:"",
            drinkIngredient13?:"",
            drinkMeasure13?:"",
            drinkIngredient14?:"",
            drinkMeasure14?:"",
            drinkIngredient15?:"",
            drinkMeasure15?:"",
            fragmentName
        )
        findNavController().navigate(action)
    }
}