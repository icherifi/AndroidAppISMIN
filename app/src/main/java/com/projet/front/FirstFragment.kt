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
import com.ismin.android.Hotel
import com.ismin.android.HotelAdapter
import com.projet.front.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment() {

    private val hotelMap = HotelMap()
    private lateinit var hotels : ArrayList<Hotel>

    private var _binding: FragmentFirstBinding? = null
    private lateinit var bookAdapter: HotelAdapter
    private lateinit var recyclerView: RecyclerView
    public lateinit var navController: NavController
    private val sharedViewModel: HotelViewModel by activityViewModels()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    private val hotelService = retrofit.create(HotelService::class.java)

    private val loadHotelMap = HotelMap()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val firstFrag = this

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView = view.findViewById(R.id.f_book_list_rcv_books)

        hotelService.getAllHotels()
            .enqueue(object : Callback<ArrayList<Hotel>> {
                override fun onResponse(
                    call: Call<ArrayList<Hotel>>,
                    response: Response<ArrayList<Hotel>>
                ) {
                    val allHotel: ArrayList<Hotel> = response.body()!!
                    for (hotel in allHotel) {
                        //println(hotel)
                        loadHotelMap.addHotel(hotel)
                    }
                    sharedViewModel.setHotels(loadHotelMap.getAllHotels())
                    val hotels = loadHotelMap.getAllHotels()

                    sharedViewModel.setHotels(hotels) //Partage de la liste des hotels aux autres layers

                    navController = findNavController()
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
}