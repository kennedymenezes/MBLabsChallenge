package com.example.mblabschallenge.login.domain.model

data class EventModel(
    val id: String,
    val event_name: String,
    val event_image: String,
    val event_city: String,
    val price: String,
    val event_state: String,
    val event_type: String,
    val event_desc: String
)
