package com.example.mblabschallenge.utils.di

import com.example.mblabschallenge.login.data.repository.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { LoginRepository()  }
}