package com.projet.front

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismin.android.Hotel

class HotelViewModel : ViewModel() {

    private val _hotels = MutableLiveData<ArrayList<Hotel>>()
    val hotels : LiveData<ArrayList<Hotel>> = _hotels

    fun getAllSharedHotels() : ArrayList<Hotel>? {
        return _hotels.value
    }

    fun setHotels(hotels: ArrayList<Hotel>) {
        _hotels.value = hotels
    }

}