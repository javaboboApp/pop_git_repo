package com.example.poptestluis.persistence

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [DBGitRepository::class, RemoteKeys::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract val gitDao: GitDao
    abstract val remoteKeysDao : RemoteKeysDao
}