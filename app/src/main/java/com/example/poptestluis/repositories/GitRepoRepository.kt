package com.example.poptestluis.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.poptestluis.models.GitRepository
import com.example.poptestluis.network.IGitRepoService
import com.example.poptestluis.persistence.AppDatabase
import com.example.poptestluis.ui.repos.paging.PageKeyedRemoteMediator
import com.example.poptestluis.utils.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow


private const val TAG = "GitRepoRepository"

interface IGitRepoRepository {
    fun getPublicRepositoriesByUser(username: String): Flow<PagingData<GitRepository>>
}

class GitRepoRepository(
    private val gitRepoService: IGitRepoService,
    private val database: AppDatabase
) : IGitRepoRepository {


    override fun getPublicRepositoriesByUser(userName: String) = Pager(
        config = PagingConfig(NETWORK_PAGE_SIZE),
        remoteMediator = PageKeyedRemoteMediator(database, gitRepoService, userName)
    ) {
        database.gitDao.getPublicRepositoriesByUser(userName)
    }.flow


}

