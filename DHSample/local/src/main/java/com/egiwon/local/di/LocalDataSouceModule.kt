package com.egiwon.local.di

import androidx.room.Room
import com.egiwon.local.impl.GithubLocalDataSourceImpl
import com.egiwon.local.source.db.GithubDataBase
import com.egiwon.repository.GithubLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataSourceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            GithubDataBase::class.java, GithubDataBase.DB_NAME
        ).build()
    }

    single { get<GithubDataBase>().githubUserDao() }
    single<GithubLocalDataSource> { GithubLocalDataSourceImpl(get()) }
}