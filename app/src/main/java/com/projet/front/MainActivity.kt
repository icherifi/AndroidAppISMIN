package com.projet.front

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.ismin.android.Hotel
import com.projet.front.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.ReadOnlyProperty

const val SERVER_BASE_URL = "https://app-c39a76fd-f893-49a2-baf7-720a2a9c0d4f.cleverapps.io/"

//test git
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    private lateinit var sharedViewModel: HotelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProvider(this).get(HotelViewModel::class.java)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = hostFragment.navController



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return when (item.itemId) {
            //R.id.action_settings -> true

            R.id.action_list -> {
                // Do something when the favorite menu item is clicked
                Toast.makeText(this, "List clicked", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_global_FirstFragment)
                return true
            }
            R.id.action_map -> {
                // Do something when the settings menu item is clicked
                Toast.makeText(this, "Map clicked", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_global_MapsFragment)
                return true
            }
            R.id.action_info -> {
                // Do something when the info menu item is clicked
                Toast.makeText(this, "Info clicked", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_global_InfoFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)


        }

    }

}