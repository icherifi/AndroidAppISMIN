package com.projet.front

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ismin.android.Hotel
import com.projet.front.databinding.FragmentFirstBinding

private const val ARG_HOTELS = "param1"

class MapsFragment : Fragment() {
    private lateinit var param1: ArrayList<Hotel>
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: HotelViewModel by activityViewModels()

    private lateinit var hotels : ArrayList<Hotel>

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        for (hotel in hotels) {
            val marker = MarkerOptions()
                .position(LatLng(hotel.X, hotel.Y))
                .title(hotel.NAME)
            googleMap.addMarker(marker)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hotels = sharedViewModel.hotels.value!!
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
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_HOTELS, hotels)
                }
            }
    }
}