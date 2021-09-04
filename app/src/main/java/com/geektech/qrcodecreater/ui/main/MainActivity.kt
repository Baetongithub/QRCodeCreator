package com.geektech.qrcodecreater.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.geektech.qrcodecreater.R
import com.geektech.qrcodecreater.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var vb: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        initNavController()
    }

    private fun initNavController() {
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.youTubeFragment,
            R.id.instagramFragment,
            R.id.TGFragment,
            R.id.VKFragment,
            R.id.anyURLFragment
        ).build()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val list: ArrayList<Int> = ArrayList()
            list.add(R.id.homeFragment)
            list.add(R.id.youTubeFragment)
            list.add(R.id.instagramFragment)
            list.add(R.id.TGFragment)
            list.add(R.id.VKFragment)
            list.add(R.id.anyURLFragment)

            if (destination.id == R.id.homeFragment) {
                window.statusBarColor = ContextCompat.getColor(this, R.color.light_green)
            } else {
                window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
}