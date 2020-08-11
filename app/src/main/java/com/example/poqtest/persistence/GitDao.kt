package com.example.poqtest.persistence

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface GitDao {
    @Query("SELECT * FROM repos")
    fun getAll(): List<DBGitRepository>

    @Query("SELECT * FROM repos")
     fun getRepositories(): PagingSource<Int, DBGitRepository>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRespositories(repositories: List<DBGitRepository>): LongArray

    @Query("DELETE FROM repos")
    suspend fun clearRepos()
}