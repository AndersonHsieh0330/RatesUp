package com.andersonhsieh.ratesup.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.andersonhsieh.ratesup.R
import com.andersonhsieh.ratesup.databinding.ActivityMainBinding
import com.andersonhsieh.ratesup.ui.home.HomeViewModel
import com.andersonhsieh.ratesup.util.CurrencyViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
//although we're not injecting anything into activity class
//we still need to add @AndroidEntryPoint because the fragment is annotated
class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var currencyViewModelFactory: CurrencyViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels { currencyViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)

        setStatusBarColorToBlack()

    }

    private fun setStatusBarColorToBlack() {
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.black)
    }
}