package com.example.mblabschallenge.login.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

class LoginRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    fun loginUserWithEmailAndPassword(email: String, password: String, callback: (FirebaseUser? , Exception?) -> Unit){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener { userLoged ->
            callback(userLoged.user, null)
        }.addOnFailureListener { exception ->
            callback(null, exception)
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String, callback: (FirebaseUser? , Exception?) -> Unit){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener { userLoged ->
            callback(userLoged.user, null)
        }.addOnFailureListener { exception ->
            callback(null, exception)
        }
    }
}