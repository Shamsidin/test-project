package com.example.worktest

import android.app.Application
import com.example.worktest.di.appModule
import com.example.worktest.di.networkModule
import com.example.worktest.di.presenterModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, networkModule, presenterModule))
    }
}