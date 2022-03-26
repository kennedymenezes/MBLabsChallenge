package com.example.mblabschallenge.dashboard.data.repository

import com.example.mblabschallenge.login.domain.mapper.EventMapper
import com.example.mblabschallenge.login.domain.model.EventModel
import com.example.mblabschallenge.utils.extensions.await
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class DashBoardRepository {

    private val firebaseDb = FirebaseFirestore.getInstance()
    private val mapper = EventMapper()

    suspend fun getEventsList(): List<EventModel> {
        val task = firebaseDb.collection("table_events").get()
        val response = task.await()
        response?.let {
            return mapper.transform(response)
        } ?: kotlin.run {
            throw Exception("Api offline")
        }
    }
}