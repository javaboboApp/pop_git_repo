package com.example.poptestluis.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.example.poptestluis.models.GitRepository
import com.example.poptestluis.repositories.GitRepoRepository
import com.example.poptestluis.repositories.IGitRepoRepository
import kotlinx.coroutines.flow.Flow

class ResposViewModel(private val repo: IGitRepoRepository) : ViewModel() {


    fun getRepos(userName: String): LiveData<PagingData<GitRepository>> {

       return repo.getPublicRepositoriesByUser(userName).asLiveData()

    }



}