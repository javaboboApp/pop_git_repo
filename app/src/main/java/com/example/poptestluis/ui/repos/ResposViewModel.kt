package com.example.poptestluis.ui.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.example.poptestluis.models.GitRepository
import com.example.poptestluis.repositories.GitRepoRepository
import com.example.poptestluis.repositories.IGitRepoRepository
import com.example.poptestluis.utils.OpenForTesting
import kotlinx.coroutines.flow.Flow

private const val TAG = "ResposViewModel"

@OpenForTesting
class ResposViewModel(private val repo: IGitRepoRepository) : ViewModel() {


    fun getRepos(userName: String): Flow<PagingData<GitRepository>> {
        Log.i(TAG, "getRepos: ")
        return repo.getPublicRepositoriesByUser(userName)

    }


}