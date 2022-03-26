package com.example.mblabschallenge.dashboard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mblabschallenge.R
import com.example.mblabschallenge.databinding.ItemEventBinding
import com.example.mblabschallenge.login.domain.model.EventModel

class DashboardAdapter(private val eventList: List<EventModel>) :
    RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        eventList[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }


    inner class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemEventBinding.bind(itemView)

        fun bind(eventModel: EventModel) {
            binding.tvEventName.text = eventModel.event_name
            Glide.with(itemView.context).load(eventModel.event_image).into(binding.ivEventImage)
        }
    }
}