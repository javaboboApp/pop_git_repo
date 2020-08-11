package com.example.poptestluis.repositories

import androidx.lifecycle.LiveData
import com.example.poptestluis.network.IGitRepoService
import com.example.poptestluis.persistence.AppDatabase




private const val TAG = "GitRepoRepository"
interface IGitRepoRepository {
    fun getPublicRepositoriesByUser(username: String)
}

class GitRepoRepository(
    private val gitRepoService: IGitRepoService,
    private val database: AppDatabase
) : IGitRepoRepository {


    override fun getPublicRepositoriesByUser(userName: String) {

    }


}

