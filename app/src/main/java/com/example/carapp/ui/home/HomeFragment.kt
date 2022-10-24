@file:Suppress("DEPRECATION")

package com.example.carapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carapp.R
import com.example.carapp.model.Drink

class HomeFragment : Fragment() {

    private lateinit var home: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeLayout: GridLayoutManager
    private lateinit var viewModel: HomeViewModel

    private var homePage = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        home = view.findViewById(R.id.list_drinks)
        homeLayout = GridLayoutManager(context, 1)
        home.layoutManager = homeLayout
        homeAdapter =
            HomeAdapter(mutableListOf()) { drink -> mostraDetalhesDrinks(drink) }
        home.adapter = homeAdapter


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.home.observe(viewLifecycleOwner, Observer { drinks ->
            homeAdapter.chamaDrinks(drinks)
            anexaDrinks()
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { onError() })
    }



    private fun onError() {
        Toast.makeText(activity, getString(R.string.home_error), Toast.LENGTH_SHORT).show()
    }

    private fun anexaDrinks() {
        home.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = homeLayout.itemCount
                val visibleItemCount = homeLayout.childCount
                val firstVisibleItem = homeLayout.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    home.removeOnScrollListener(this)
                    homePage++
                    /*viewModel.getDrinkById(homePage)*/
                }
            }
        })
    }

    private fun mostraDetalhesDrinks(drink: Drink) {
        // intents para a tela de detalhes
    }


}