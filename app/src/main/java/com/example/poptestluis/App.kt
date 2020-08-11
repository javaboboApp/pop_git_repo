package com.example.poptestluis

import android.app.Application
import com.example.poptestluis.di.databaseModule
import com.example.poptestluis.di.networkModule
import com.example.poptestluis.di.repositoriesModule
import com.example.poptestluis.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
private val appComponent = mutableListOf(databaseModule, networkModule,repositoriesModule, viewModelModule)

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