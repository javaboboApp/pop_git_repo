package com.example.poqtest.di

import com.example.poqtest.network.IGitRepoService
import com.example.poqtest.persistence.AppDatabase
import com.example.poqtest.repositories.GitRepoRepository
import com.example.poqtest.repositories.IGitRepoRepository
import org.koin.dsl.module

fun provideGitRepoRepository(gitRepoService: IGitRepoService,
                          database: AppDatabase
): IGitRepoRepository {
    return GitRepoRepository(gitRepoService, database)
}

val repositoriesModule = module {

    single { provideGitRepoRepository(get(), get()) }
}
