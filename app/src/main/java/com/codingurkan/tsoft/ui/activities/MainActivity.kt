package com.codingurkan.tsoft.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.NavigationUI
import com.codingurkan.tsoft.R
import com.codingurkan.tsoft.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        bottomNavigation()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    private fun bottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHost
        navController = navHostFragment.navController

        binding?.let {
            bottomNavigationView = it.bottomNavigationView
            NavigationUI.setupWithNavController(bottomNavigationView, navController)
        }
    }
}