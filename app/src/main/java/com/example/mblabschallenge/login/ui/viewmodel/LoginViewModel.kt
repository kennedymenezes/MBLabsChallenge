package com.example.mblabschallenge.login.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mblabschallenge.login.data.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _LoginUiState = MutableLiveData<FirebaseUser>()
    val loginState: LiveData<FirebaseUser> = _LoginUiState

    private val _LoginErrorUiState = MutableLiveData<Exception>()
    val loginErrorState: LiveData<Exception> = _LoginErrorUiState


    fun loginWithEmailAndPassword(email: String, password: String) =
        loginRepository.loginUserWithEmailAndPassword(email, password) { firebaseUser, exception ->
            if (firebaseUser == null) {
                _LoginErrorUiState.value = exception
            }
            if (exception == null) {
                _LoginUiState.value = firebaseUser
            }
        }
}