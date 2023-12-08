package com.projet.front

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.projet.front.databinding.FragmentDetailBinding

/**
 Contains the detail of a Hotel, can change properties of hotel.
 */

private const val ARG_HOTEL = "param1"

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private lateinit var param1: Hotel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        param1 = arguments?.getSerializable("param1") as Hotel //get hotel clicked from recycle view
        Log.i("CLICK", "param1: $param1")

        //update view with hotel properties
        val nameHotel = param1.NAME
        val address = param1.ADDRESS
        val zip = param1.ZIPCODE

        view.findViewById<TextView>(R.id.textview_name).text = nameHotel
        view.findViewById<TextView>(R.id.textview_address).text = address
        view.findViewById<TextView>(R.id.textview_zipcode).text = zip.toString()

        //adapt the text of button for add or remove from favorites
        val viewStar = view.findViewById<TextView>(R.id.button_fav)
        if (param1.isFavorite) viewStar.text = context?.resources?.getString(R.string.supprfav)
        mainActivity = this.activity as MainActivity

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_DetailFragment_to_HotelsFragment)
        }

        binding.buttonFav.setOnClickListener {
            mainActivity.onUpdateFavorite(param1)
            val viewStar = view.findViewById<TextView>(R.id.button_fav)
            if (viewStar.text == context?.resources?.getString(R.string.supprfav)){
                viewStar.text = context?.resources?.getString(R.string.addfav)
            }
            else viewStar.text = context?.resources?.getString(R.string.supprfav)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}