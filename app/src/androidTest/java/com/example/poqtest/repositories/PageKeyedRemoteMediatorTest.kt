package com.example.poqtest.repositories

import androidx.paging.*
import com.example.poqtest.network.IGitRepoService
import com.example.poqtest.persistence.DBGitRepository
import com.example.poqtest.repositories.paging.PageKeyedRemoteMediator
import com.example.poqtest.utils.DatabaseTest
import com.example.poqtest.utils.NETWORK_PAGE_SIZE
import com.example.poqtest.utils.TestUtil
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

//I believe that PageKeyedRemoteMediatorTest(belong to the repository package) is really impant so I decided to test it
//The repository does not have many stuff to test...

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
