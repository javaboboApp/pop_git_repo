package com.example.poqtest.persistence

import android.content.Context
import androidx.room.Room

object DatabaseFactory  {

    fun getDBInstance(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "gitRepo.db")
            .fallbackToDestructiveMigration()
            .build()

}