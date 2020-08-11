package com.example.poqtest.di

import com.example.poqtest.ui.repos.RepoListAdapter
import org.koin.dsl.module

val adapters = module{
    single { RepoListAdapter() }
}
