package com.example.mblabschallenge.dashboard.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mblabschallenge.R
import com.example.mblabschallenge.dashboard.domain.interfaces.ClickEvent
import com.example.mblabschallenge.dashboard.ui.adapter.DashboardAdapter
import com.example.mblabschallenge.dashboard.ui.viewmodel.DashboardViewModel
import com.example.mblabschallenge.databinding.DashboardFragmentBinding
import com.example.mblabschallenge.eventdetails.ui.fragment.EventDetailsFragment
import com.example.mblabschallenge.login.domain.model.EventModel
import com.example.mblabschallenge.utils.di.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.dashboard_fragment), ClickEvent {

    private val viewModel: DashboardViewModel by viewModel()
    private lateinit var binding: DashboardFragmentBinding
    private val loadingDialog = LoadingDialog()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DashboardFragmentBinding.bind(view)
        setupObservers()
        viewModel.getEventList()
        loadingDialog.show(requireActivity().supportFragmentManager, null)
    }

    private fun setupObservers(){
        viewModel.eventList.observe(viewLifecycleOwner){
            loadingDialog.dismiss()
            setupEventsList(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            Toast.makeText(requireContext(), "Um erro aconteceu", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupEventsList(eventList: List<EventModel>){
        binding.rvDashboard.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvDashboard.adapter = DashboardAdapter(eventList, this)
    }

    override fun eventClicked(eventModel: EventModel) {
        requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, EventDetailsFragment(eventModel)).commit()
    }

}