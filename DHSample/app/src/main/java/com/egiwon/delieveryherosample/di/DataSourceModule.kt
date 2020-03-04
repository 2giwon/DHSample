package com.egiwon.delieveryherosample.di

import androidx.room.Room
import com.egiwon.delieveryherosample.data.source.GithubRepository
import com.egiwon.delieveryherosample.data.source.GithubRepositoryImpl
import com.egiwon.delieveryherosample.data.source.local.GithubLocalDataSource
import com.egiwon.delieveryherosample.data.source.local.GithubLocalDataSourceImpl
import com.egiwon.delieveryherosample.data.source.local.db.GithubDataBase
import com.egiwon.delieveryherosample.data.source.remote.GithubRemoteDataSource
import com.egiwon.delieveryherosample.data.source.remote.GithubRemoteDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            GithubDataBase::class.java, GithubDataBase.DB_NAME
        ).build()
    }

    single { get<GithubDataBase>().githubUserDao() }
    single<GithubLocalDataSource> { GithubLocalDataSourceImpl(get()) }
    single<GithubRemoteDataSource> {
        GithubRemoteDataSourceImpl(get())
    }
    single<GithubRepository> { GithubRepositoryImpl(get(), get()) }
}