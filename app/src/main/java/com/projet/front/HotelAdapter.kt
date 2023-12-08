package com.projet.front

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismin.android.HotelViewHolder


class HotelAdapter(private var hotels: List<Hotel>, private var fragment: HotelsFragment) : RecyclerView.Adapter<HotelViewHolder>() {

    public lateinit var hotel: Hotel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.hotel_sum, parent, false)
        return HotelViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        //convert object Hotel to view holder
        hotel = hotels[position]
        holder.itemView.setOnTouchListener(HandleTouch(hotel, this.fragment))
        holder.txvName.text = hotel.NAME
        holder.txvNumberRooms.text = hotel.NUMROOMS.toString()
        holder.txvZipcode.text = hotel.ZIPCODE.toString()
        holder.imvFavorite.visibility = if (hotel.isFavorite) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    class HandleTouch(private val hotel: Hotel, private val fragment: HotelsFragment ) : View.OnTouchListener {
    //Managing the clicks on the view
        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            //Handle of click events on hotels view
            //Move to detail fragment when clicking
            when (event!!.action) {
                MotionEvent.ACTION_DOWN -> {
                    v?.alpha = 0.5f
                }
                MotionEvent.ACTION_UP -> {
                    v?.alpha = 1f
                    val bundle = Bundle().apply {
                        putSerializable("param1", hotel)
                    }
                    fragment.navController.navigate(R.id.action_HotelsFragment_to_DetailFragment, bundle)
                }

            }
            return true
        }
    }

}


