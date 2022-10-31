package com.example.carapp.ui.details

import android.os.Bundle
import android.util.Log
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
        binding.tvDrinkName.setText("${args.drinkName}")
        binding.tvInstructions.setText("${args.drinkInstructions}")

        if ({args.drinkIngredient1} == null){
            binding.tvIngredient1.visibility = View.GONE

        } else{
            binding.tvIngredient1.setText("${args.drinkMeasure1} ${args.drinkIngredient1}")
        }

        if ({args.drinkIngredient2} == null){
            binding.tvIngredient2.visibility = View.GONE

        } else{
            binding.tvIngredient2.setText("${args.drinkMeasure2} ${args.drinkIngredient2}")
        }

        if ({args.drinkIngredient3} == null){
            binding.tvIngredient3.visibility = View.GONE
        } else{
            binding.tvIngredient3.setText("${args.drinkMeasure3} ${args.drinkIngredient3}")

        }

        if ({args.drinkIngredient4} == null){
            binding.tvIngredient4.visibility = View.GONE
        } else{
            binding.tvIngredient4.setText("${args.drinkMeasure4} ${args.drinkIngredient4}")

        }

        if ({args.drinkIngredient5} == null){
            binding.tvIngredient5.visibility = View.GONE
        } else{
            binding.tvIngredient5.setText("${args.drinkMeasure5} ${args.drinkIngredient5}")
        }

        if ({args.drinkIngredient6} == null){
            binding.tvIngredient6.visibility = View.GONE
        } else{
            binding.tvIngredient6.setText("${args.drinkMeasure6} ${args.drinkIngredient6}")
        }

        if ({args.drinkIngredient7} == null){
            binding.tvIngredient7.visibility = View.GONE
        } else{
            binding.tvIngredient7.setText("${args.drinkMeasure7} ${args.drinkIngredient7}")
        }

        if ({args.drinkIngredient8} == null){
            binding.tvIngredient8.visibility = View.GONE
        } else{
            binding.tvIngredient8.setText("${args.drinkMeasure8} ${args.drinkIngredient8}")
        }

        if ({args.drinkIngredient9} != null){
            binding.tvIngredient9.setText("${args.drinkMeasure9} ${args.drinkIngredient9}")
        } else{
            binding.tvIngredient9.visibility = View.GONE
        }

        if ({args.drinkIngredient10} != null){
            binding.tvIngredient10.setText("${args.drinkMeasure10} ${args.drinkIngredient10}")
        } else{
            binding.tvIngredient10.visibility = View.GONE
        }

        if ({args.drinkIngredient11} != null){
            binding.tvIngredient11.setText("${args.drinkMeasure11} ${args.drinkIngredient11}")
        } else{
            binding.tvIngredient11.visibility = View.GONE
        }

        if ({args.drinkIngredient12} != null){
            binding.tvIngredient12.setText("${args.drinkMeasure12} ${args.drinkIngredient12}")
        } else{
            binding.tvIngredient12.visibility = View.GONE
        }

        if ({args.drinkIngredient13} != null){
            binding.tvIngredient13.setText("${args.drinkMeasure13} ${args.drinkIngredient13}")
        } else{
            binding.tvIngredient13.visibility = View.GONE
        }

        if ({args.drinkIngredient14} != null){
            binding.tvIngredient14.setText("${args.drinkMeasure14} ${args.drinkIngredient14}")
        } else{
            binding.tvIngredient14.visibility = View.GONE
        }

        if ({args.drinkIngredient15} != null){
            binding.tvIngredient15.setText("${args.drinkMeasure15} ${args.drinkIngredient15}")
        } else{
            binding.tvIngredient15.visibility = View.GONE
        }

        Glide.with(binding.root).load(args.drinkImage).centerCrop()
            .into(binding.ivDrink)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )

        viewViewModel = ViewModelProvider(this).get(ViewViewModel::class.java)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveAndReturn(): Boolean {
       args.fragmentname.toLowerCase() == "homefragment"
            findNavController().navigateUp()
            return true

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewViewModel::class.java)

    }



}