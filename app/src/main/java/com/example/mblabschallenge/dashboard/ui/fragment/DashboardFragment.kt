package com.example.mblabschallenge.dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mblabschallenge.R
import com.example.mblabschallenge.dashboard.ui.adapter.DashboardAdapter
import com.example.mblabschallenge.dashboard.ui.viewmodel.DashboardViewModel
import com.example.mblabschallenge.databinding.DashboardFragmentBinding
import com.example.mblabschallenge.login.domain.model.EventModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.dashboard_fragment) {

    private val viewModel: DashboardViewModel by viewModel()
    private lateinit var binding: DashboardFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DashboardFragmentBinding.bind(view)
        setupObservers()
        viewModel.getEventList()
    }

    private fun setupObservers(){
        viewModel.eventList.observe(viewLifecycleOwner){
            setupEventsList(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Um erro aconteceu", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupEventsList(eventList: List<EventModel>){
        binding.rvDashboard.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvDashboard.adapter = DashboardAdapter(eventList)
    }

}