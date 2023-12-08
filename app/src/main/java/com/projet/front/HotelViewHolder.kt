package com.ismin.android

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projet.front.R

class HotelViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) { //view holder for hotel in RecyclerView
    var txvName: TextView = rootView.findViewById(R.id.r_hotel_name)
    var txvZipcode: TextView = rootView.findViewById(R.id.r_hotel_zipcode)
    var txvNumberRooms: TextView = rootView.findViewById(R.id.r_hotel_number_rooms)
    var imvFavorite: ImageView = rootView.findViewById(R.id.r_hotel_star_logo)
}