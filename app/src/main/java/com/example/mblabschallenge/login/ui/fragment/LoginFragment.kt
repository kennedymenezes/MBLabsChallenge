package com.example.mblabschallenge.login.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.mblabschallenge.R
import com.example.mblabschallenge.dashboard.ui.fragment.DashboardFragment
import com.example.mblabschallenge.databinding.LoginFragmentBinding
import com.example.mblabschallenge.login.ui.MainActivity
import com.example.mblabschallenge.login.ui.viewmodel.LoginViewModel
import com.example.mblabschallenge.utils.di.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.login_fragment) {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: LoginFragmentBinding
    private val loadingDialog = LoadingDialog()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)
        setupObservers()
        setupListeners()
    }

    private fun setupListeners(){
        binding.btLogin.setOnClickListener {
            viewModel.loginWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            loadingDialog.show(requireActivity().supportFragmentManager, null)
        }

        binding.btRegister.setOnClickListener {
            (requireActivity() as MainActivity).changeFragment(RegisterFragment())
        }
    }

    private fun setupObservers(){
        viewModel.loginState.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).changeFragment(DashboardFragment())
        }

        viewModel.loginErrorState.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
        }

    }
}