package com.example.poptestluis.persistence

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.example.poptestluis.models.GitRepository
import kotlinx.coroutines.flow.Flow

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