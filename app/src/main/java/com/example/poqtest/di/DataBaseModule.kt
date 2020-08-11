package com.example.poqtest.di

import android.app.Application
import androidx.room.Room
import com.example.poqtest.persistence.AppDatabase
import org.koin.dsl.module

fun provideDatabase(application: Application): AppDatabase {
    return  Room.databaseBuilder(application, AppDatabase::class.java, "gitRepo.db")
        .fallbackToDestructiveMigration()
        .build()
}


val databaseModule = module {
    single { provideDatabase(get())}
}
