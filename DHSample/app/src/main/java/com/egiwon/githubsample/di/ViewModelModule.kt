package com.egiwon.githubsample.di

import com.egiwon.githubsample.ui.GithubSharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { GithubSharedViewModel(get()) }
}