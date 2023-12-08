package com.projet.front
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface HotelService {
    @GET("/findHotels") //Service use to find hotels from the database
    fun getAllHotels(): Call<ArrayList<Hotel>>
    @PUT("/favorite/{id}") //Service use to update the favorites status of a hotel
    fun updateFavorite(@Path("id") hotelId: String, @Body() hotel: Hotel) : Call<ArrayList<Hotel>>
}
