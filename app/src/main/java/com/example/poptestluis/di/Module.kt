package com.example.poptestluis.di

import com.example.poptestluis.models.GitRepository
import com.example.poptestluis.network.IGitRepoService
import com.example.poptestluis.network.RetrofitInstance
import com.example.poptestluis.persistence.DatabaseFactory
import com.example.poptestluis.repositories.GitRepoRepository
import com.example.poptestluis.repositories.IGitRepoRepository
import com.example.poptestluis.ui.repos.RepoListAdapter
import com.example.poptestluis.ui.repos.ResposViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    single { DatabaseFactory.getDBInstance(get()) }
}


val networkModule = module {
    single { RetrofitInstance().retrofitGitRepo() }
}

val repositoriesModule = module {
    single<IGitRepoRepository> { GitRepoRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel  { ResposViewModel(get()) }
}

val adapters = module{
    single { RepoListAdapter() }
}
