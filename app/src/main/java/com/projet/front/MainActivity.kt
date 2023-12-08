package com.projet.front

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.projet.front.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val SERVER_BASE_URL = "https://app-c39a76fd-f893-49a2-baf7-720a2a9c0d4f.cleverapps.io/"

/** 
 Main activity, manage the first load of data, and navigation buttons. We use navigation controllers.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var hostFragment: NavHostFragment
    private lateinit var sharedViewModel: HotelViewModel
    
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()
    private val hotelService = retrofit.create(HotelService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProvider(this)[HotelViewModel::class.java]


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = hostFragment.navController
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    private fun loadHotels() {
        //Refresh hotels function, get data from database and load it into cache
        val sharedPreferences = this.getSharedPreferences("hotels", MODE_PRIVATE)
        val loadHotelMap = HotelMap()
        hotelService.getAllHotels()
            .enqueue(object : Callback<ArrayList<Hotel>> {
                override fun onResponse(
                    call: Call<ArrayList<Hotel>>,
                    response: Response<ArrayList<Hotel>>
                ) {
                    val allHotel: ArrayList<Hotel> = response.body()!!
                    for (hotel in allHotel) {
                        loadHotelMap.addHotel(hotel)
                    }
                    val hotels = loadHotelMap.getAllHotels()
                    sharedViewModel.setHotels(hotels) //Refresh cache

                    val cachedHotelsString = Gson().toJson(loadHotelMap)
                    sharedPreferences.edit()
                        .putString("cachedHotels", cachedHotelsString)
                        .apply()

                    navController.navigate(R.id.action_global_FirstFragment)
                }

                override fun onFailure(call: Call<ArrayList<Hotel>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun onUpdateFavorite(hotel: Hotel) {
        val hotelService: HotelService = retrofit.create(HotelService::class.java)
        println("onUpdateFavorite")
        println(hotel)
        var updatehotel = hotel
        updatehotel.isFavorite = hotel.isFavorite.not()
        hotelService.updateFavorite(hotel._id, updatehotel)
            .enqueue {
                onFailure = {
                    val code = it.hashCode()
                    println("Eror code: $code")
                }
            }
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

            R.id.action_refresh -> {
                // Refresh when the icon is clicked
                Toast.makeText(this, "Refresh..", Toast.LENGTH_SHORT).show()
                loadHotels()
                return true
            }
            else -> super.onOptionsItemSelected(item)


        }

    }

}