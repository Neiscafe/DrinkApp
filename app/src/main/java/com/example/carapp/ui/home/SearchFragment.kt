package com.example.carapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.carapp.databinding.FragmentSearchBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: FragmentSearchBinding

@Suppress("DEPRECATION")
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        val searchView = binding.searchView;

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val action = SearchFragmentDirections.actionViewHome(p0.toString())
                findNavController().safeNavigate(action)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("Search text:", "$newText")
                return false
            }
        })

        return binding.root
    }

    fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}