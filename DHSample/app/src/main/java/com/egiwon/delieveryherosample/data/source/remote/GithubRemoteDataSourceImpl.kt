package com.egiwon.delieveryherosample.data.source.remote

import com.egiwon.delieveryherosample.data.UserLikeResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubRemoteDataSourceImpl(
    private val service: GithubSearchLikeService
) : GithubRemoteDataSource {

    override fun searchGithubUser(query: String): Single<UserLikeResponse> =
        service.getUserInfo(query)
            .subscribeOn(Schedulers.io())
            .retry(1)

}