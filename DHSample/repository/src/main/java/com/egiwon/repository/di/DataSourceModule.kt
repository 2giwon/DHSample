package com.egiwon.repository.di

import com.egiwon.repository.GithubRepository
import com.egiwon.repository.impl.GithubRepositoryImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<GithubRepository> { GithubRepositoryImpl(get(), get()) }
}