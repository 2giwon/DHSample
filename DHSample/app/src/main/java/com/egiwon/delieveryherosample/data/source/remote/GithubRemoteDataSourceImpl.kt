package com.egiwon.delieveryherosample.data.source.remote

import com.egiwon.delieveryherosample.BuildConfig
import com.egiwon.delieveryherosample.data.AccessToken
import com.egiwon.delieveryherosample.data.UserLikeResponse
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubRemoteDataSourceImpl(
        private val service: GithubSearchLikeService,
        private val authService: AuthApiService
) : GithubRemoteDataSource {

    override fun searchGithubUser(query: String): Flowable<UserLikeResponse> =
            service.getUserInfo(query)
                    .onBackpressureBuffer()
                    .subscribeOn(Schedulers.io())

    override fun requestAccessToken(code: String): Single<AccessToken> =
            authService.getAccessToken(
                    BuildConfig.GithubClientId,
                    BuildConfig.GithubClientSecret,
                    code
            ).subscribeOn(Schedulers.io())

}