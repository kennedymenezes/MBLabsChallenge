package com.example.mblabschallenge.login.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.mblabschallenge.R
import com.example.mblabschallenge.databinding.LoginFragmentBinding
import com.example.mblabschallenge.login.ui.MainActivity
import com.example.mblabschallenge.login.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.login_fragment) {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: LoginFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)
        setupObservers()
        setupListeners()
    }

    private fun setupListeners(){
        binding.btLogin.setOnClickListener {
            viewModel.loginWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }

        binding.btRegister.setOnClickListener {
            (requireActivity() as MainActivity).changeFragment(RegisterFragment())
        }
    }

    private fun setupObservers(){
        viewModel.loginState.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_SHORT).show()
        }

        viewModel.loginErrorState.observe(viewLifecycleOwner) {
            println(it)
            Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
        }

    }
}