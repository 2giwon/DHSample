package com.egiwon.delieveryherosample.data.source

import com.egiwon.delieveryherosample.data.User
import com.egiwon.delieveryherosample.data.UserLikeResponse
import io.reactivex.Completable
import io.reactivex.Single

interface GithubRepository {
    fun searchUserInfo(query: String): Single<UserLikeResponse>

    fun addLikeUser(user: User): Completable

    fun removeLikeUser(user: User): Completable

    fun getLikeUser(): Single<List<User>>

    fun searchLikeUsers(query: String): Single<List<User>>
}