package com.example.poqtest.repositories

import android.util.Log
import androidx.paging.*
import com.example.poqtest.mappers.asDomain
import com.example.poqtest.models.GitRepository
import com.example.poqtest.network.IGitRepoService
import com.example.poqtest.persistence.AppDatabase
import com.example.poqtest.repositories.paging.PageKeyedRemoteMediator
import com.example.poqtest.utils.NETWORK_PAGE_SIZE
import com.example.poqtest.utils.OpenForTesting
import kotlinx.coroutines.flow.Flow


private const val TAG = "GitRepoRepository"

interface IGitRepoRepository {
    fun getPublicRepositoriesByUser(username: String): Flow<PagingData<GitRepository>>
}
@OpenForTesting
class GitRepoRepository(
    private val gitRepoService: IGitRepoService,
    private val database: AppDatabase
) : IGitRepoRepository {


    override fun getPublicRepositoriesByUser(userName: String) = Pager(
        config = PagingConfig(NETWORK_PAGE_SIZE, enablePlaceholders = true),
        remoteMediator = PageKeyedRemoteMediator(database, gitRepoService, userName)
    ) {
        Log.i(TAG, "getRepositories: ")
       database.gitDao.getRepositories()
    }.asDomain()


}



