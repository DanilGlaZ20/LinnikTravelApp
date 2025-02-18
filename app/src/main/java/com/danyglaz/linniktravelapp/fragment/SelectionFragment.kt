package com.danyglaz.linniktravelapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.danyglaz.linniktravelapp.R


class SelectionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val firstRouteFragment=view.findViewById<ImageView>(R.id.firstRoute)
        val secondRouteFragment=view.findViewById<ImageView>(R.id.secondRoute)
        val controller=findNavController()
        firstRouteFragment.setOnClickListener { controller.navigate(R.id.firstRouteFragment) }
        secondRouteFragment.setOnClickListener { controller.navigate(R.id.secondRouteFragment) }

    }

}