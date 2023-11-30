package com.projet.front

import com.ismin.android.Hotel
import retrofit2.Call
import retrofit2.http.GET

interface HotelService {
    @GET("/findHotels")
    fun getAllHotels(): Call<ArrayList<Hotel>>

}