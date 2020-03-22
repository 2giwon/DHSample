package com.egiwon.repository

import com.egiwon.repository.model.UserDomainEntity
import io.reactivex.Completable
import io.reactivex.Single

interface GithubLocalDataSource {
    fun getLikeUsers(): Single<List<UserDomainEntity>>

    fun addLikeUser(userDomain: UserDomainEntity): Completable

    fun removeLikeUser(userDomain: UserDomainEntity): Completable

    fun searchLikeUsers(query: String): Single<List<UserDomainEntity>>
}