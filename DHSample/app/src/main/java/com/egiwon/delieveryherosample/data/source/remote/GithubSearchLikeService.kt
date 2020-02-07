package com.egiwon.delieveryherosample.data.source.remote

import com.egiwon.delieveryherosample.BuildConfig
import com.egiwon.delieveryherosample.data.UserLikeResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubSearchLikeService {
    @GET("/search/users")
    fun getUserInfo(
            @Query("q")
            userID: String,
            @Query("client_id")
            clientId: String = BuildConfig.GithubClientId,
            @Query("client_secret")
            clientSecret: String = BuildConfig.GithubClientSecret
    ): Flowable<UserLikeResponse>
}