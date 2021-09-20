package com.manohar.colaninfotechtask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.manohar.colaninfotechtask.R
import com.manohar.colaninfotechtask.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.homeToolBar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController!!.graph)
        setupActionBarWithNavController(navController!!, appBarConfiguration)
        NavigationUI.setupActionBarWithNavController(this, navController!!)

    }

    override fun onSupportNavigateUp(): Boolean {
        navController!!.navigateUp()
        return super.onSupportNavigateUp()
    }
}