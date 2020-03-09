package com.egiwon.delieveryherosample.data.source.local

import com.egiwon.delieveryherosample.data.User
import com.egiwon.delieveryherosample.data.source.local.db.GithubUserDao
import com.egiwon.delieveryherosample.data.source.local.model.UserEntity
import com.egiwon.delieveryherosample.data.source.local.model.mapperToUser
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubLocalDataSourceImpl(
    private val githubUserDao: GithubUserDao
) : GithubLocalDataSource {

    override fun getLikeUsers(): Single<List<User>> =
        githubUserDao.getLikeUsers()
            .map { userList -> userList.map { it.mapperToUser() } }
            .subscribeOn(Schedulers.io())

    override fun setLikeUser(user: User): Completable =
        githubUserDao.setLikeUser(
            UserEntity(
                user.id,
                user.avatarUrl,
                user.name,
                user.score,
                user.like
            )
        ).subscribeOn(Schedulers.io())

    override fun removeLikeUser(user: User): Completable =
        githubUserDao.removeLikeUser(
            UserEntity(
                user.id,
                user.avatarUrl,
                user.name,
                user.score,
                user.like
            )
        ).subscribeOn(Schedulers.io())

    override fun searchLikeUsers(query: String): Single<List<User>> =
        githubUserDao.searchLikeUsers(query)
            .map { userList -> userList.map { it.mapperToUser() } }
            .subscribeOn(Schedulers.io())

}