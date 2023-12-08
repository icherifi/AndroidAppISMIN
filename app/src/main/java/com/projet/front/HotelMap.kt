package com.projet.front

import java.lang.IllegalArgumentException

class HotelMap { //Not so much used, I prefer to use ArrayList<Hotel>, but can be maybe more efficient to use HashMap

    private val storage = HashMap<Int, Hotel>()

    fun addHotel(hotel: Hotel) {
        storage[hotel.OBJECTID] = hotel
    }

    fun getHotel(id: Int): Hotel {
        return storage[id] ?: throw IllegalArgumentException("Unknown id: $id")
    }

    fun getAllHotels(): ArrayList<Hotel> {
        return ArrayList(storage.values
            .sortedBy { hotel -> hotel.NAME }) //Sort by name Hotel,
    }

    fun getTotalNumberOfHotels(): Int {
        return storage.size
    }
}
