package com.egiwon.delieveryherosample.data.source.remote

import com.egiwon.delieveryherosample.data.UserLikeResponse
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubRemoteDataSourceImpl(
        private val service: GithubSearchLikeService
) : GithubRemoteDataSource {
    override fun searchGithubUser(query: String): Flowable<UserLikeResponse> =
            service.getUserInfo(query)
                    .onBackpressureBuffer()
                    .subscribeOn(Schedulers.io())

}