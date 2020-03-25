package com.egiwon.githubsample

import android.app.Application
import com.egiwon.githubsample.di.viewModelModule
import com.egiwon.local.di.localDataSourceModule
import com.egiwon.remote.di.networkModule
import com.egiwon.remote.di.remoteDataSourceModule
import com.egiwon.repository.di.dataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class GithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            logger(if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger())
            androidContext(this@GithubApplication)
            modules(
                viewModelModule,
                dataSourceModule,
                networkModule,
                remoteDataSourceModule,
                localDataSourceModule
            )
        }
    }
}