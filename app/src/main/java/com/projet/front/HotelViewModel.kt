package com.projet.front

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class HotelViewModel : ViewModel() {

    private val _hotels = MutableLiveData<ArrayList<Hotel>?>()

    fun getAllSharedHotels() : ArrayList<Hotel>? {
        return _hotels.value
    }

    fun setHotels(hotels: ArrayList<Hotel>?) {
        _hotels.value = hotels
    }
}