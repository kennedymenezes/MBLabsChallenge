package com.example.mblabschallenge.login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mblabschallenge.R
import com.example.mblabschallenge.databinding.MainActivityBinding
import com.example.mblabschallenge.login.ui.fragment.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(LoginFragment())
    }

    fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }
}