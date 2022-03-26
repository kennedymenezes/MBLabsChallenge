package com.example.mblabschallenge.utils.application

import android.app.Application
import com.example.mblabschallenge.utils.di.repositoryModule
import com.example.mblabschallenge.utils.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppStart : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppStart)
            modules(repositoryModule, viewModelModule)
        }
    }
}