package com.example.mblabschallenge.utils.di

import com.example.mblabschallenge.login.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(loginRepository = get()) }
}