package com.example.poqtest.ui
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.paging.PagingData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.poqtest.R
import com.example.poqtest.ui.repos.BaseFragment
import com.example.poqtest.ui.repos.RepoListAdapter
import com.example.poqtest.ui.repos.ReposFragment
import com.example.poqtest.ui.repos.ResposViewModel
import com.example.poqtest.utils.DatabaseTest
import com.example.poqtest.utils.TestUtil
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
@InternalCoroutinesApi
class ReposFragmentTest : DatabaseTest(){



    @Mock
    private lateinit var uiCommunicatorInterface : BaseFragment.CommunicatorsInterface
     val repoListAdapter: RepoListAdapter = RepoListAdapter()


    @Mock
    private lateinit var resposViewModel: ResposViewModel




    private fun initFragment(): FragmentScenario<ReposFragment> {
        return launchFragmentInContainer(
            fragmentArgs = null, // Bundle
            themeResId = R.style.AppTheme

        )

    }

    @Before
    fun setUpEach() {
        MockitoAnnotations.initMocks(this)


        //INIT KOIN AND OVERRIDE THE VIEWMODEL
        loadKoinModules(
            module {

                viewModel(override = true) {
                    resposViewModel
                }

                single(override = true) {
                    repoListAdapter
                }
            })
        // Create a graphical FragmentScenario
     val fragmentScenario =   initFragment()
        fragmentScenario.onFragment {
            fragment ->
            fragment.uiCommunicatorInterface = uiCommunicatorInterface
        }

    }

    @Test
    fun test_hideProgressBar_is_called_after_we_get_an_empty_list() = runBlocking {
         repoListAdapter.submitData(PagingData.empty())
        Mockito.verify(uiCommunicatorInterface).hideProgressBar()
    }

    @Test
    fun test_hideProgressBar_is_called_after_the_list_is_set() = runBlocking {
        repoListAdapter.submitData(PagingData.from(TestUtil.TEST_LIST_GIT_REPO))
        Mockito.verify(uiCommunicatorInterface).hideProgressBar()
    }



    @Test
    fun testFragmentIsVisible() {
        onView(withId(R.id.fragment_repos)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewIsVisible() {
        onView(withId(R.id.repos_list_recyclerview)).check(matches(isDisplayed()))
    }


}