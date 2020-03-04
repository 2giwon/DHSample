package com.egiwon.delieveryherosample.data.source.local

import com.egiwon.delieveryherosample.data.User
import io.reactivex.Completable
import io.reactivex.Single

interface GithubLocalDataSource {
    fun getLikeUsers(): Single<List<User>>

    fun setLikeUser(user: User): Completable

    fun removeLikeUser(user: User): Completable

    fun searchLikeUsers(query: String): Single<List<User>>
}