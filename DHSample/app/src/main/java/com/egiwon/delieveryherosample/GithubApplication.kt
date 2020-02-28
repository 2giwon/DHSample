package com.egiwon.delieveryherosample

import android.app.Application
import com.egiwon.delieveryherosample.di.dataSourceModule
import com.egiwon.delieveryherosample.di.networkModule
import com.egiwon.delieveryherosample.di.viewModelModule
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
                listOf(
                    viewModelModule,
                    dataSourceModule,
                    networkModule
                )
            )
        }
    }
}