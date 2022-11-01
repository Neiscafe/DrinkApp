package com.example.carapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.carapp.R
import com.example.carapp.databinding.ViewFragmentBinding
import com.example.carapp.model.Drink
import com.example.carapp.ui.home.HomeViewModel

@Suppress("DEPRECATION", "UnusedEquals")
class ViewFragment : Fragment() {
    private lateinit var viewModel: ViewViewModel
    private lateinit var binding: ViewFragmentBinding
    private lateinit var viewViewModel: ViewViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var responseJson: String
    private lateinit var spinner: ProgressBar
    private lateinit var myJson: String

    private val args: ViewFragmentArgs by navArgs()
    private val drink: Drink? by lazy { args.drink }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_check)
        }

        setHasOptionsMenu(true)

        binding = ViewFragmentBinding.inflate(inflater, container, false);
        binding.tvDrinkName.setText("${drink?.strDrink}")
        binding.tvInstructions.setText("${drink?.strInstructions}")

        if (drink?.strIngredient1 == null){
            binding.tvIngredient1.visibility = View.GONE

        } else{
            binding.tvIngredient1.setText("${drink?.strMeasure1} ${drink?.strIngredient1}")
        }

        if (drink?.strIngredient2 == null){
            binding.tvIngredient2.visibility = View.GONE

        } else{
            binding.tvIngredient2.setText("${drink?.strMeasure2} ${drink?.strIngredient2}")
        }

        if (drink?.strIngredient3 == null){
            binding.tvIngredient3.visibility = View.GONE
        } else{
            binding.tvIngredient3.setText("${drink?.strMeasure3} ${drink?.strIngredient3}")

        }

        if (drink?.strIngredient4 == null){
            binding.tvIngredient4.visibility = View.GONE
        } else{
            binding.tvIngredient4.setText("${drink?.strMeasure4} ${drink?.strIngredient4}")

        }

        if (drink?.strIngredient5 == null){
            binding.tvIngredient5.visibility = View.GONE
        } else{
            binding.tvIngredient5.setText("${drink?.strMeasure5} ${drink?.strIngredient5}")
        }

        if (drink?.strIngredient6 == null){
            binding.tvIngredient6.visibility = View.GONE
        } else{
            binding.tvIngredient6.setText("${drink?.strMeasure6} ${drink?.strIngredient6}")
        }

        if (drink?.strIngredient7 == null){
            binding.tvIngredient7.visibility = View.GONE
        } else{
            binding.tvIngredient7.setText("${drink?.strMeasure7} ${drink?.strIngredient7}")
        }

        if (drink?.strIngredient8 == null){
            binding.tvIngredient8.visibility = View.GONE
        } else{
            binding.tvIngredient8.setText("${drink?.strMeasure8} ${drink?.strIngredient8}")
        }

        if (drink?.strIngredient9 != null){
            binding.tvIngredient9.setText("${drink?.strMeasure9} ${drink?.strIngredient9}")
        } else{
            binding.tvIngredient9.visibility = View.GONE
        }

        if (drink?.strIngredient10 != null){
            binding.tvIngredient10.setText("${drink?.strMeasure10} ${drink?.strIngredient10}")
        } else{
            binding.tvIngredient10.visibility = View.GONE
        }

        if (drink?.strIngredient11 != null){
            binding.tvIngredient11.setText("${drink?.strMeasure11} ${drink?.strIngredient11}")
        } else{
            binding.tvIngredient11.visibility = View.GONE
        }

        if (drink?.strIngredient12 != null){
            binding.tvIngredient12.setText("${drink?.strMeasure12} ${drink?.strIngredient12}")
        } else{
            binding.tvIngredient12.visibility = View.GONE
        }

        if (drink?.strIngredient13 != null){
            binding.tvIngredient13.setText("${drink?.strMeasure13} ${drink?.strIngredient13}")
        } else{
            binding.tvIngredient13.visibility = View.GONE
        }

        if (drink?.strIngredient14 != null){
            binding.tvIngredient14.setText("${drink?.strMeasure14} ${drink?.strIngredient14}")
        } else{
            binding.tvIngredient14.visibility = View.GONE
        }

        if (drink?.strIngredient15 != null){
            binding.tvIngredient15.setText("${drink?.strMeasure15} ${drink?.strIngredient15}")
        } else{
            binding.tvIngredient15.visibility = View.GONE
        }

        Glide.with(binding.root).load(drink?.strDrinkThumb).centerCrop()
            .into(binding.ivDrink)

        viewViewModel = ViewModelProvider(this).get(ViewViewModel::class.java)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> findNavController().popBackStack()
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewViewModel::class.java)

    }



}