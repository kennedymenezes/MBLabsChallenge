package com.example.mblabschallenge.dashboard.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mblabschallenge.dashboard.data.repository.DashBoardRepository
import com.example.mblabschallenge.login.domain.model.EventModel
import kotlinx.coroutines.launch

class DashboardViewModel(private val dashBoardRepository: DashBoardRepository): ViewModel() {

    private val _eventList = MutableLiveData<List<EventModel>>()
    val eventList: LiveData<List<EventModel>> = _eventList

    private val _error = MutableLiveData<Exception>()
    val error: LiveData<Exception> = _error

    fun getEventList(){
        viewModelScope.launch {
            try {
                _eventList.value = dashBoardRepository.getEventsList()
            } catch (e: Exception){
                _error.value = e
            }

        }
    }
}