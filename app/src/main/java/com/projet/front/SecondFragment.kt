package com.projet.front

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.ismin.android.Hotel
import com.projet.front.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

private const val ARG_HOTEL = "param1"

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var param1: Hotel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root

        param1 = arguments?.getSerializable("param1") as Hotel
        Log.i("CLICK", "param1: $param1")


        val nameHotel = param1.NAME as String

        val textViewSecond = view.findViewById<TextView>(R.id.textview_second)
        textViewSecond.text = nameHotel

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(hotel: Hotel) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_HOTEL, hotel)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}