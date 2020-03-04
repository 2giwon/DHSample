package com.egiwon.delieveryherosample.data.source.remote

import com.egiwon.delieveryherosample.data.UserLikeResponse
import io.reactivex.Single

interface GithubRemoteDataSource {
    fun searchGithubUser(query: String): Single<UserLikeResponse>
}