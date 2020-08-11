package com.example.poptestluis.persistence

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.poptestluis.models.GitRepository
import kotlinx.coroutines.flow.Flow

@Dao
interface GitDao {

    @Query("SELECT * FROM DBGitRepository")
     fun getPublicRepositoriesByUser(userName: String): PagingSource<Int, GitRepository>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRespositories(repositories: List<DBGitRepository>): LongArray

    @Query("DELETE FROM DBGitRepository")
    suspend fun clearRepos()
}