package com.egiwon.remote.di

import com.egiwon.remote.impl.GithubRemoteDataSourceImpl
import com.egiwon.repository.GithubRemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<GithubRemoteDataSource> {
        GithubRemoteDataSourceImpl(get())
    }
}