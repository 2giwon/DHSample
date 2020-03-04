package com.egiwon.delieveryherosample.di

import com.egiwon.delieveryherosample.BuildConfig
import com.egiwon.delieveryherosample.data.source.remote.GithubSearchLikeService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    factory { (chain: Interceptor.Chain) ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .build()
        )
    }

    single<CallAdapter.Factory> {
        RxJava2CallAdapterFactory.create()
    }
    single<Converter.Factory> {
        GsonConverterFactory.create()
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://api.github.com/")
            .client(get<OkHttpClient>())
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
    }

    single {
        get<Retrofit>().create(GithubSearchLikeService::class.java)
    }
}