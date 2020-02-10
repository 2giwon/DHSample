package com.egiwon.delieveryherosample.data.source.remote

import com.egiwon.delieveryherosample.data.AccessToken
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {
    @FormUrlEncoded
    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    fun getAccessToken(
            @Field("client_id")
            clientId: String,
            @Field("client_secret")
            clientSecret: String,
            @Field("code")
            code: String
    ): Single<AccessToken>
}