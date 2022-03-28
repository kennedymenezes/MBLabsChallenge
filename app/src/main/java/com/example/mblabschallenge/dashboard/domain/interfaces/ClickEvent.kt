package com.example.mblabschallenge.dashboard.domain.interfaces

import com.example.mblabschallenge.login.domain.model.EventModel

interface ClickEvent {
    fun eventClicked(eventModel: EventModel)
}