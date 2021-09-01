package com.geektech.qrcodecreater.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
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
            R.id.mainFragment,
            R.id.youTubeFragment,
            R.id.instagramFragment,
            R.id.TGFragment,
            R.id.VKFragment,
            R.id.anyURLFragment
        ).build()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean =
        NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
}