package com.example.mblabschallenge.dashboard.domain.mapper

import com.example.mblabschallenge.login.domain.model.EventModel
import com.example.mblabschallenge.utils.interfaces.IMapper
import com.google.firebase.firestore.QuerySnapshot

class EventMapper : IMapper<QuerySnapshot, List<EventModel>> {
    override fun transform(entry: QuerySnapshot): List<EventModel> {
        val eventList = mutableListOf<EventModel>()
        entry.forEach { eventResponseFirebase ->
            eventList.add(
                EventModel(
                    id = eventResponseFirebase.id,
                    event_name = eventResponseFirebase["event_name"] as String,
                    event_image = eventResponseFirebase["event_image"] as String,
                    event_city = eventResponseFirebase["event_city"] as String,
                    event_state = eventResponseFirebase["event_state"] as String,
                    event_type = eventResponseFirebase["event_type"] as String,
                    price = eventResponseFirebase["event_price"] as String,
                    event_desc = eventResponseFirebase["event_desc"] as String
                )
            )
        }
        return eventList
    }
}