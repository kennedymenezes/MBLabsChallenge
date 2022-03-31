package com.example.mblabschallenge.eventdetails.ui.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.example.mblabschallenge.R
import com.example.mblabschallenge.databinding.EventDetailsFragmentBinding
import com.example.mblabschallenge.eventdetails.ui.viewmodel.EventDetailsViewModel
import com.example.mblabschallenge.login.domain.model.EventModel
import com.example.mblabschallenge.login.ui.MainActivity
import org.koin.android.ext.android.inject
import java.util.jar.Manifest

class EventDetailsFragment(private val eventModel: EventModel) :
    Fragment(R.layout.event_details_fragment) {

    private var hasImage = false

    private val getPhoto =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK){
                binding.ivVacine.visibility = View.VISIBLE
                binding.ivVacine.setImageBitmap(it.data?.extras?.get("data") as Bitmap)
                hasImage = true
                validateFields()
            }

        }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permission ->
            if (permission){
                getPhoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            } else {
                Toast.makeText(requireContext(), "Por favor permita o uso da camera", Toast.LENGTH_SHORT).show()
            }
        }

    private lateinit var binding: EventDetailsFragmentBinding


    private val viewModel: EventDetailsViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventDetailsFragmentBinding.bind(view)
        loadEventInfo()
        loadSpinner()
        loadListeners()
        setupObservers()
    }


    private fun loadListeners() {
        binding.cbHalfEntry.setOnClickListener {
            if (binding.cbHalfEntry.isChecked) {
                binding.etHalfEntry.visibility = View.VISIBLE
            } else {
                binding.etHalfEntry.visibility = View.GONE
            }
            validateFields()
        }

        binding.etHalfEntry.doOnTextChanged { _, _, _, _ ->
            validateFields()
        }

        binding.etCardFields.doOnTextChanged { _, _, _, _ ->
            validateFields()
        }

        binding.mbtBuy.setOnClickListener {
            (requireActivity() as MainActivity).changeFragment(ConfirmationFragment())
        }

        binding.mbtPhoto.setOnClickListener {
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askForPermission()
        } else {
            getPhoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }

    private fun askForPermission() {
        requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
    }

    private fun setupObservers() {
        viewModel.enableContinueButton.observe(viewLifecycleOwner) {
            binding.mbtBuy.isEnabled = it
        }
    }

    private fun loadSpinner() {
        binding.sprPaymentMethod.adapter = ArrayAdapter(
            requireContext(),
            org.koin.android.R.layout.support_simple_spinner_dropdown_item,
            listOf("Pix", "Boleto", "Credito", "Debito", "Deposito")
        )

        binding.sprPaymentMethod.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 == 2 || p2 == 3) {
                        binding.etCardFields.visibility = View.VISIBLE
                    } else {
                        binding.etCardFields.visibility = View.GONE
                    }
                    validateFields()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }
    }

    private fun loadEventInfo() {
        Glide.with(requireContext()).load(eventModel.event_image).into(binding.ivEventImage)
        binding.tvEventName.text = eventModel.event_name
        binding.tvEventPrice.text = eventModel.price
        binding.tvEventState.text = eventModel.event_state
        binding.tvEventCity.text = eventModel.event_city
        binding.tvEventType.text = eventModel.event_type
        binding.tvEventDesc.text = eventModel.event_desc
    }

    private fun validateFields() {
        viewModel.validateFields(
            binding.sprPaymentMethod.selectedItemPosition,
            binding.cbHalfEntry.isChecked,
            binding.etHalfEntry.text.toString(),
            binding.etCardFields.text.toString(),
            hasImage
        )

    }

}