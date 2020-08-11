package com.example.poptestluis.repositories

import android.util.Log
import androidx.paging.*
import com.example.poptestluis.mappers.asDatabaseModel
import com.example.poptestluis.mappers.asDomain
import com.example.poptestluis.mappers.asDomainModel
import com.example.poptestluis.network.IGitRepoService
import com.example.poptestluis.persistence.DBGitRepository
import com.example.poptestluis.ui.repos.paging.PageKeyedRemoteMediator
import com.example.poptestluis.utils.DatabaseTest
import com.example.poptestluis.utils.NETWORK_PAGE_SIZE
import com.example.poptestluis.utils.TestUtil
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException
import java.lang.Exception

class PageKeyedRemoteMediatorTest : DatabaseTest() {

    @Mock
    private lateinit var iGitService: IGitRepoService

    private lateinit var pageKeyedRemoteMediator: PageKeyedRemoteMediator


    @Before
    fun setUpEach() {
        MockitoAnnotations.initMocks(this);
        pageKeyedRemoteMediator =
            PageKeyedRemoteMediator(db = db, gitRepoService = iGitService, userName = "")
    }

    @ExperimentalPagingApi
    @Test
    fun test_when_success_datas_we_get_a_success_answer() {
        runBlocking {
            //Arrange
            val response = TestUtil.TEST_LIST_GIT_REPONSE__REPO

            //Act
            Mockito.`when`(iGitService.getRepository(anyString(), anyInt(), anyInt())).thenReturn(response)
            //Verify
            val pag = PagingState<Int, DBGitRepository>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(NETWORK_PAGE_SIZE, enablePlaceholders = true)
                , leadingPlaceholderCount = 0
            )
            val valueReturned: RemoteMediator.MediatorResult =
                pageKeyedRemoteMediator.load(LoadType.REFRESH, pag)

            Mockito.verify(iGitService).getRepository(anyString(), anyInt(), anyInt())

            assertTrue(valueReturned is RemoteMediator.MediatorResult.Success)

        }}


        @ExperimentalPagingApi
        @Test
        fun test_when_error_datas_we_get_a_error_answer() {
            runBlocking {
                //Arrange
                val response = TestUtil.TEST_LIST_GIT_REPONSE__REPO

                //Act
                Mockito.`when`(iGitService.getRepository(anyString(), anyInt(), anyInt()))
                    .thenReturn(null)
                //Verify
                val pag = PagingState<Int, DBGitRepository>(
                    pages = listOf(),
                    anchorPosition = null,
                    config = PagingConfig(NETWORK_PAGE_SIZE, enablePlaceholders = true)
                    , leadingPlaceholderCount = 0
                )
                val valueReturned: RemoteMediator.MediatorResult =
                    pageKeyedRemoteMediator.load(LoadType.REFRESH, pag)

                Mockito.verify(iGitService).getRepository(anyString(), anyInt(), anyInt())

                assertTrue(valueReturned is RemoteMediator.MediatorResult.Error)

            }
        }


}
