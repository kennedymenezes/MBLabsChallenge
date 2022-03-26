package com.example.mblabschallenge.dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mblabschallenge.R
import com.example.mblabschallenge.dashboard.ui.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.dashboard_fragment) {

    private val viewModel: DashboardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getEventList()
    }

    private fun setupObservers(){
        viewModel.eventList.observe(viewLifecycleOwner){
            println(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Um erro aconteceu", Toast.LENGTH_SHORT).show()
        }
    }

}