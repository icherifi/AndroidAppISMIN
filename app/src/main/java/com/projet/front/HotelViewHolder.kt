package com.ismin.android

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projet.front.R

class HotelViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    var txvName = rootView.findViewById<TextView>(R.id.r_hotel_name)
    var txvZipcode = rootView.findViewById<TextView>(R.id.r_hotel_zipcode)
    var txvNumberRooms= rootView.findViewById<TextView>(R.id.r_hotel_number_rooms)
}