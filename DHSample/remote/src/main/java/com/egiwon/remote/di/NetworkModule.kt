package com.egiwon.remote.di

import com.egiwon.remote.BuildConfig
import com.egiwon.remote.service.GithubSearchLikeService
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

    factory {
        get<Retrofit>().create(GithubSearchLikeService::class.java)
    }
}