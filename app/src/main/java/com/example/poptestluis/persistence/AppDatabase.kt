package com.example.poptestluis.persistence

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [DBGitRepository::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val popDao: PopDao
}