package com.egiwon.remote.service

import com.egiwon.remote.response.UserLikeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubSearchLikeService {
    @GET("/search/users")
    fun getUserInfo(
        @Query("q")
        userID: String
    ): Single<UserLikeResponse>
}