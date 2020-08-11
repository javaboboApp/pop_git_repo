package com.example.poptestluis.network

import com.example.poptestluis.network.responses.GitRepositoryResponse
import com.example.poptestluis.utils.GET_REPOSITORIES_ENDPOINT
import com.example.poptestluis.utils.QUERY_PAGE
import com.example.poptestluis.utils.PATH_USER_NAME
import com.example.poptestluis.utils.QUERY_PER_PAGE

import retrofit2.http.*

interface IGitRepoService {

    @GET(GET_REPOSITORIES_ENDPOINT)
    suspend fun getRepository(
        @Path(PATH_USER_NAME) userName: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PER_PAGE) per_page : Int
    ) : List<GitRepositoryResponse>

}