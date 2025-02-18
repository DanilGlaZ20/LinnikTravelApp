package com.danyglaz.linniktravelapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.danyglaz.linniktravelapp.R

class FirstRouteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parkGpsFragment = view.findViewById<TextView>(R.id.parkgps)
        val controller = findNavController()
        parkGpsFragment.setOnClickListener { controller.navigate(R.id.parkGpsFragment) }

    }
}