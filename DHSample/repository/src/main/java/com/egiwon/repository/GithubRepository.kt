package com.egiwon.repository

import com.egiwon.repository.model.UserDomainEntity
import io.reactivex.Completable
import io.reactivex.Single

interface GithubRepository {
    fun searchUserInfo(query: String): Single<List<UserDomainEntity>>

    fun addLikeUser(userDomain: UserDomainEntity): Completable

    fun removeLikeUser(userDomain: UserDomainEntity): Completable

    fun getLikeUser(): Single<List<UserDomainEntity>>

    fun searchLikeUsers(query: String): Single<List<UserDomainEntity>>
}