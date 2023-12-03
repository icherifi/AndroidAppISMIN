package com.projet.front

import java.lang.IllegalArgumentException

class HotelMap {

    private val storage = HashMap<Int, Hotel>()

    fun addHotel(hotel: Hotel) {
        storage[hotel.OBJECTID] = hotel
    }

    fun getHotel(id: Int): Hotel {
        val hotel = storage[id]
        if (hotel == null) {
            throw IllegalArgumentException("Unknown id: $id")
        }
        return hotel
    }

    fun getAllHotels(): ArrayList<Hotel> {
        return ArrayList(storage.values
            .sortedBy { book -> book.NAME })
    }

    fun getTotalNumberOfHotels(): Int {
        return storage.size
    }
}
