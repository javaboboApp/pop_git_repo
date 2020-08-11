package com.example.poptestluis.ui.repos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.poptestluis.R
import kotlinx.android.synthetic.main.fragment_repos.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.time.LocalDate

private const val TAG = "ReposFragment"

@InternalCoroutinesApi
class ReposFragment : BaseFragment() {

    val reposViewModel: ResposViewModel by viewModel()
    val repoListAdapter: RepoListAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initRecyclerView()
        initGetRepos()
    }

    private fun initAdapter() {

        //We have to check if there was an error
        repoListAdapter.addLoadStateListener { loadState ->
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    //show the progressbar when state is LOADING
                    uiCommunicatorInterface?.showProgressBar()
                }

                is LoadState.Error -> {
                    //Show a toast message when we have an error
                    showErrorMsg()
                    uiCommunicatorInterface?.hideProgressBar()
                }

                is LoadState.NotLoading -> {
                    //Not loading is emitted hide progress bar
                    uiCommunicatorInterface?.hideProgressBar()

                }
            }
        }

    }

    private fun initRecyclerView() {
        repos_list_recyclerview.apply {
            adapter = repoListAdapter
        }
    }

    private fun initGetRepos() {
       //get the repositories of the for an user called square "
        lifecycleScope.launch {

            reposViewModel.getRepos("square").collectLatest { pagingGitRepository ->
                Log.i(TAG, "initGetRepos: ")
                repoListAdapter.submitData(pagingGitRepository)

            }
        }


    }

    private fun showErrorMsg() {
        Toast.makeText(requireContext(), getString(R.string.error_msg), Toast.LENGTH_LONG).show()
    }


}