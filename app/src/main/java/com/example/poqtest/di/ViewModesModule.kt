package com.example.poqtest.di

import com.example.poqtest.ui.repos.ResposViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module




val viewModelModule = module {
    viewModel  { ResposViewModel(get()) }
}

