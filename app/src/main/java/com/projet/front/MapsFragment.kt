package com.projet.front

import android.content.Context
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.projet.front.databinding.FragmentHotelsBinding

private const val ARG_HOTELS = "param1"

/**
 * Fragment, display map with markers corresponding to hotels.
 */
class MapsFragment : Fragment() {
    private lateinit var param1: ArrayList<Hotel>
    private var _binding: FragmentHotelsBinding? = null
    private val binding get() = _binding!!
    private lateinit var hotels : ArrayList<Hotel>
    private val callback = OnMapReadyCallback { googleMap ->

        val washington = LatLng(38.9071923, -77.0368707)
        googleMap.setMinZoomPreference(12.0f)
        googleMap.addMarker(MarkerOptions().position(washington).title("Marker in Washington DC"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(washington))

        for (hotel in hotels) {
            val marker = MarkerOptions()
                .position(LatLng(hotel.Y, hotel.X))
                .title(hotel.NAME)
            googleMap.addMarker(marker)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreferences = requireContext().getSharedPreferences("hotels",
            Context.MODE_PRIVATE
        )
        val cachedHotelsString = sharedPreferences.getString("cachedHotels", null)
        if (cachedHotelsString != null) {
            hotels = Gson().fromJson(cachedHotelsString, HotelMap::class.java).getAllHotels()}
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        @JvmStatic
        fun newInstance(hotels: ArrayList<Hotel>) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_HOTELS, hotels)
                }
            }
    }
}