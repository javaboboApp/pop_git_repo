package com.example.poptestluis.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.poptestluis.R
import com.example.poptestluis.mappers.asDomain
import com.example.poptestluis.models.GitRepository
import com.example.poptestluis.network.IGitRepoService
import com.example.poptestluis.ui.repos.ReposFragment
import com.example.poptestluis.ui.repos.ResposViewModel
import com.example.poptestluis.ui.repos.paging.PageKeyedRemoteMediator
import com.example.poptestluis.utils.DatabaseTest
import com.example.poptestluis.utils.NETWORK_PAGE_SIZE
import com.example.poptestluis.utils.TestUtil
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@InternalCoroutinesApi
class ReposFragmentTest : DatabaseTest(){

    private lateinit var fragmentScenario: FragmentScenario<ReposFragment>

    @Mock
    private lateinit var gitRepoService: IGitRepoService

    @Mock
    private lateinit var resposViewModel: ResposViewModel

   val fakeFlow = flow {

       emit( Pager(
           config = PagingConfig(NETWORK_PAGE_SIZE, enablePlaceholders = true),
           remoteMediator = PageKeyedRemoteMediator(db, gitRepoService, "test")
       ) {
           db.gitDao.getRepositories()
       }.asDomain())

    }

    private fun initFragment(): FragmentScenario<ReposFragment> {
        return launchFragmentInContainer(
            fragmentArgs = null, // Bundle
            themeResId = R.style.AppTheme
        )

    }

    @Before
    fun setUpEach() = runBlocking {
        MockitoAnnotations.initMocks(this)
        // Create a graphical FragmentScenario
         fragmentScenario = initFragment()

        //INIT KOIN AND OVERRIDE THE VIEWMODEL
        loadKoinModules(
            module {

                viewModel(override = true) {
                    resposViewModel
                }
            })

        Mockito.doReturn(TestUtil.TEST_LIST_GIT_REPONSE__REPO).`when`(gitRepoService).getPublicRepositoriesByUser(anyString(), 1)
        Mockito.doReturn(fakeFlow).`when`(resposViewModel).getRepos(anyString())
    }

    @Test
    fun testLoading() = runBlocking{

        //Mockito.verify()

    }
}