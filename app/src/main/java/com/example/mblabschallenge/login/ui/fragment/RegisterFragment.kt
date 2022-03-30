package com.example.mblabschallenge.login.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mblabschallenge.R
import com.example.mblabschallenge.databinding.RegisterFragmentBinding
import com.example.mblabschallenge.login.ui.viewmodel.RegisterViewModel
import com.example.mblabschallenge.utils.di.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment(R.layout.register_fragment) {

    private lateinit var binding: RegisterFragmentBinding
    private val viewModel: RegisterViewModel by viewModel()
    private val loadingDialog = LoadingDialog()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RegisterFragmentBinding.bind(view)
        setupObservers()
        setupListeners()
    }

    private fun setupListeners(){
        binding.btRegister.setOnClickListener {
            viewModel.register(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            loadingDialog.show(requireActivity().supportFragmentManager, null)
        }
        binding.btCancel.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setupObservers(){
        viewModel.loginState.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }

        viewModel.loginErrorState.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
        }
    }
}