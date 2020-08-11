package com.example.poqtest.network

import com.example.poqtest.network.responses.GitRepositoryResponse
import com.example.poqtest.utils.GET_REPOSITORIES_ENDPOINT
import com.example.poqtest.utils.QUERY_PAGE
import com.example.poqtest.utils.PATH_USER_NAME
import com.example.poqtest.utils.QUERY_PER_PAGE

import retrofit2.http.*

interface IGitRepoService {

    @GET(GET_REPOSITORIES_ENDPOINT)
    suspend fun getRepository(
        @Path(PATH_USER_NAME) userName: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PER_PAGE) per_page : Int
    ) : List<GitRepositoryResponse>

}