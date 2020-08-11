package com.example.poqtest.ui.repos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.poqtest.models.GitRepository
import com.example.poqtest.repositories.IGitRepoRepository
import com.example.poqtest.utils.OpenForTesting
import kotlinx.coroutines.flow.Flow

private const val TAG = "ResposViewModel"

@OpenForTesting
class ResposViewModel(private val repo: IGitRepoRepository) : ViewModel() {


    fun getRepos(userName: String): Flow<PagingData<GitRepository>> {
        Log.i(TAG, "getRepos: ")
        return repo.getPublicRepositoriesByUser(userName)

    }


}