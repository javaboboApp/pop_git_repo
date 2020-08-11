package com.example.poptestluis.network

import androidx.lifecycle.LiveData
import com.example.poptestluis.network.responses.GitRepositoryResponse
import com.example.poptestluis.utils.GET_REPOSITORIES_ENDPOINT
import com.example.poptestluis.utils.PATH_PAGE
import com.example.poptestluis.utils.PATH_USER_NAME

import retrofit2.http.*

interface IGitRepoService {

    @GET(GET_REPOSITORIES_ENDPOINT)
    suspend fun getPublicRepositoriesByUser(
        @Path(PATH_USER_NAME) userName: String,
        @Query(PATH_PAGE) page: Int
    ) : List<GitRepositoryResponse>

}