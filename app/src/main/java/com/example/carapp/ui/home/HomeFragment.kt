package com.example.carapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
        id: Int,
        drinkName: String,
        drinkInstructions: String,
        drinkImage: String,
        fragmentName: String
    ) {
        val action = HomeFragmentDirections.actionViewDrink(
            id,
            drinkName,
            drinkInstructions,
            drinkImage,
            fragmentName
        )
        findNavController().navigate(action)
    }
}