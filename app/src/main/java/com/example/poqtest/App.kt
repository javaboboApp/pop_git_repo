package com.example.poqtest

import android.app.Application
import com.example.poqtest.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

private val appComponent = mutableListOf(databaseModule, apiModule,repositoriesModule, viewModelModule, adapters)

    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(applicationContext)
            // declare modules
            modules(appComponent)
        }
    }

}