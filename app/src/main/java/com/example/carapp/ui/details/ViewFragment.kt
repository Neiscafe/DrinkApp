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
import com.bumptech.glide.Glide
import com.example.carapp.R
import com.example.carapp.databinding.ViewFragmentBinding
import com.example.carapp.ui.home.HomeViewModel
import androidx.navigation.fragment.navArgs as navArgs

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