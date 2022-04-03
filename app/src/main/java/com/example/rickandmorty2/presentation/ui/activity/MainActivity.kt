package com.example.rickandmorty2.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.rickandmorty2.R
import com.example.rickandmorty2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.characterFragment,
            R.id.locationFragment,
            R.id.episodeFragment
        ).build()
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
    }

}