package com.mgm.imdb250movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mgm.imdb250movies.R
import com.mgm.imdb250movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding
    //Navigation
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Navigation
            navController = findNavController(R.id.navHost)
            bottomNav.setupWithNavController(navController)
            /*appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController,appBarConfiguration)*/
            //Show bottom navigation
            navController.addOnDestinationChangedListener{ _, destination, _ ->
                if (destination.id == R.id.splashFragment || destination.id == R.id.registerFragment|| destination.id == R.id.detailFragment){
                    bottomNav.visibility = View.GONE
                }else{
                    bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}