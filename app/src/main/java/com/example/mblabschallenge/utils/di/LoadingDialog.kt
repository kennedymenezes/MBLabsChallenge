package com.example.mblabschallenge.utils.di

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.mblabschallenge.R
import com.example.mblabschallenge.databinding.FragmentLoadingDialogBinding

class LoadingDialog() :
    DialogFragment() {

    companion object {
        private const val TAG = "loading_dialog"
    }

    private lateinit var binding: FragmentLoadingDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.fragment_loading_dialog, null)
        binding = FragmentLoadingDialogBinding.bind(view)

        val dialog = AlertDialog.Builder(activity)
        dialog.setView(view)

        return dialog.create()
    }

}