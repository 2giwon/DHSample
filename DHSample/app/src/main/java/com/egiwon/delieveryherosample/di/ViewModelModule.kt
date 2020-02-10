package com.egiwon.delieveryherosample.di

import com.egiwon.delieveryherosample.MainViewModel
import com.egiwon.delieveryherosample.ui.GithubSharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

    viewModel { GithubSharedViewModel(get()) }
}