package com.example.mblabschallenge.eventdetails.ui.viewmodel

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EventDetailsViewModel : ViewModel() {


    private val _enableContinueButton = MutableLiveData<Boolean>()
    val enableContinueButton: LiveData<Boolean> = _enableContinueButton

    fun validateFields(paymentMethodSelection: Int, halfEntry: Boolean, halfEntryText: String, cardNumber: String, hasImage: Boolean){
        var halfOk = false
        var cardOk = false

        halfOk = if (halfEntry){
            halfEntryText.isNotBlank()
        } else {
            true
        }

        cardOk = if (paymentMethodSelection == 2 || paymentMethodSelection == 3){
            cardNumber.isNotBlank()
        } else {
            true
        }
        _enableContinueButton.value = halfOk && cardOk && hasImage
    }
}