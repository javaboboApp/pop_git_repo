package com.example.poqtest.utils

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.poqtest.persistence.AppDatabase
import com.example.poqtest.persistence.GitDao
import org.junit.After
import org.junit.Before
import java.lang.Exception

//util class to test the local database
open class DatabaseTest {
    // system under test
    protected lateinit var db: AppDatabase
    val gitDao: GitDao
        get() = db.gitDao

    @Before
    fun init() {
        //Create a temporally database
        db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun finish() {
        try {
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}