package com.egiwon.delieveryherosample.data.source

import com.egiwon.delieveryherosample.data.AccessToken
import com.egiwon.delieveryherosample.data.User
import com.egiwon.delieveryherosample.data.UserLikeResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface GithubRepository {
    fun searchUserInfo(query: String): Flowable<UserLikeResponse>

    fun setLikeUser(user: User): Completable

    fun removeLikeUser(user: User): Completable

    fun getLikeUser(): Flowable<List<User>>

    fun searchLikeUsers(query: String): Flowable<List<User>>

    fun requestAccessToken(code: String): Single<AccessToken>
}