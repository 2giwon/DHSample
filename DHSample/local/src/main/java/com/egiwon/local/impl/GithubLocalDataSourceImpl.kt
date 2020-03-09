package com.egiwon.local.impl

import com.egiwon.local.source.db.GithubUserDao
import com.egiwon.local.source.model.UserEntity
import com.egiwon.local.source.model.mapperToUser
import com.egiwon.repository.GithubLocalDataSource
import com.egiwon.repository.model.UserDomainEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubLocalDataSourceImpl(
    private val githubUserDao: GithubUserDao
) : GithubLocalDataSource {

    override fun getLikeUsers(): Single<List<UserDomainEntity>> =
        githubUserDao.getLikeUsers()
            .map { userList -> userList.map { it.mapperToUser() as UserDomainEntity } }
            .subscribeOn(Schedulers.io())

    override fun addLikeUser(userDomain: UserDomainEntity): Completable =
        githubUserDao.setLikeUser(
            UserEntity(
                userDomain.id,
                userDomain.avatarUrl,
                userDomain.name,
                userDomain.score,
                userDomain.like
            )
        ).subscribeOn(Schedulers.io())

    override fun removeLikeUser(userDomain: UserDomainEntity): Completable =
        githubUserDao.removeLikeUser(
            UserEntity(
                userDomain.id,
                userDomain.avatarUrl,
                userDomain.name,
                userDomain.score,
                userDomain.like
            )
        ).subscribeOn(Schedulers.io())

    override fun searchLikeUsers(query: String): Single<List<UserDomainEntity>> =
        githubUserDao.searchLikeUsers(query)
            .map { userList -> userList.map { it.mapperToUser() as UserDomainEntity } }
            .subscribeOn(Schedulers.io())

}