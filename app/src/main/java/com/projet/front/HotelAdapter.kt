package com.ismin.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projet.front.FirstFragment
import com.projet.front.Hotel
import com.projet.front.R


class HotelAdapter(private var hotels: List<Hotel>, private var fragment: FirstFragment) : RecyclerView.Adapter<HotelViewHolder>() {

    public lateinit var hotel: Hotel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.hotel_sum, parent, false)
        return HotelViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        hotel = hotels[position]
        holder.itemView.setOnTouchListener(HandleTouch(hotel, this.fragment))
        holder.txvName.text = hotel.NAME
        holder.txvNumberRooms.text = hotel.NUMROOMS.toString()
        holder.txvZipcode.text = hotel.ZIPCODE.toString()
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    fun updateBooks(allHotels: List<Hotel>) {
        hotels = allHotels
        notifyDataSetChanged()
    }
    class HandleTouch(private val hotel: Hotel, private val fragment: FirstFragment ) : View.OnTouchListener {

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            //Handle of click events on hotels view
            when (event!!.action) {
                MotionEvent.ACTION_DOWN -> {
                    v?.alpha = 0.5f
                }
                MotionEvent.ACTION_UP -> {
                    v?.alpha = 1f
                    val bundle = Bundle().apply {
                        putSerializable("param1", hotel)
                    }
                    fragment.navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

                }

            }
            return true
        }
    }

}


