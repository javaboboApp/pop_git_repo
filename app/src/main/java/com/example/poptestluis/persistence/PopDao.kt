package com.example.poptestluis.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PopDao {


//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertUser(user: DBUser): Long
//
//    @Delete
//    fun deleteUser(user: DBUser) : Int
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertRespositories(repositories:  List<DBGitRepository>): LongArray
//
//    @Query("SELECT * FROM DBGitRepository WHERE owner_name =:userName AND private_repo = 'public'")
//    fun getPublicRepositoriesByUser(userName: String):LiveData<List<DBGitRepository>>
//
//    @Query("SELECT * FROM DBGitRepository WHERE owner_name =:userName ")
//    fun getPrivateRepositories(userName: String):LiveData<List<DBGitRepository>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertCommits(commits: List<DBCommit>): LongArray
//
//    @Query("SELECT * FROM DBCommit WHERE repo_id =:repoId ORDER BY timestamp DESC")
//    fun getCommitsByRepoId( repoId: Long):LiveData<List<DBCommit>>


}