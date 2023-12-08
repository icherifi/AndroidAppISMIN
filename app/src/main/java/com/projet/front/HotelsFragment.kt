package com.projet.front

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projet.front.databinding.FragmentHotelsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
    Contains the list of hotel as a RecyclerView, can view some information on the hotel
 */

class HotelsFragment : Fragment() {

    private var _binding: FragmentHotelsBinding? = null
    private lateinit var bookAdapter: HotelAdapter
    private lateinit var recyclerView: RecyclerView
    lateinit var navController: NavController
    private val sharedViewModel: HotelViewModel by activityViewModels()
    private lateinit var hotels : ArrayList<Hotel>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val firstFrag = this

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHotelsBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView = view.findViewById(R.id.f_book_list_rcv_books)
        navController = findNavController()

        //connection to the api
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SERVER_BASE_URL)
            .build()
        val hotelService = retrofit.create(HotelService::class.java)
        val loadHotelMap = HotelMap()

        hotelService.getAllHotels() //get hotel form database
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
                    sharedViewModel.setHotels(hotels) //Save in cache the hotels

                    setupRecyclerView(hotels)

                }
                override fun onFailure(call: Call<ArrayList<Hotel>>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(hotels: ArrayList<Hotel>) {
        bookAdapter = HotelAdapter(hotels, firstFrag)
        recyclerView.adapter = bookAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

}