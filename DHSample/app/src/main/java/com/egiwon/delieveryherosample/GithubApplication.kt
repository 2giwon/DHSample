package com.egiwon.delieveryherosample

import android.app.Application
import com.egiwon.delieveryherosample.di.viewModelModule
import com.egiwon.local.di.localDataSourceModule
import com.egiwon.remote.di.networkModule
import com.egiwon.remote.di.remoteDataSourceModule
import com.egiwon.repository.di.dataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GithubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
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