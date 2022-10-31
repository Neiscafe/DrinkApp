package com.example.carapp

import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.view.Window
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.carapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val animationSearchFragment: NavOptions
        get() = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_from_left)
            .setExitAnim(R.anim.anim_to_right)
            .setPopEnterAnim(R.anim.anim_to_left)
            .setPopExitAnim(R.anim.anim_to_right)
            .build()
//
//    private val animationDashboardFragment: NavOptions
//        get() = NavOptions.Builder()
//            .setEnterAnim(R.anim.anim_to_right)
//            .setExitAnim(R.anim.anim_to_left)
//            .setPopEnterAnim(R.anim.anim_from_left)
//            .setPopExitAnim(R.anim.anim_from_right)
//            .build()

    private val animationNotificationsFragment: NavOptions
        get() = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_to_right)
            .setExitAnim(R.anim.anim_to_left)
            .setPopEnterAnim(R.anim.anim_from_left)
            .setPopExitAnim(R.anim.anim_from_right)
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            enterTransition = Explode()
            allowEnterTransitionOverlap
        }
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment



        navView.setupWithNavController(navHostFragment.navController)
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.searchFragment -> {
                    if (R.id.searchFragment != navHostFragment.navController.currentDestination?.id) {
                        navHostFragment.navController.navigate(
                            R.id.searchFragment,
                            null,
                            animationSearchFragment
                        )
                    }
                }
//                R.id.navigation_dashboard -> {
//                    navHostFragment.navController.navigate(R.id.navigation_dashboard, null, animationDashboardFragment)
//                }
                R.id.navigation_notifications -> {
                    if (R.id.navigation_notifications != navHostFragment.navController.currentDestination?.id) {
                        navHostFragment.navController.navigate(
                            R.id.navigation_notifications,
                            null,
                            animationNotificationsFragment
                        )
                    }
                }
            }
            true
        }
    }
}