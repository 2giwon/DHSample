package com.egiwon.delieveryherosample.data.source.local

import com.egiwon.delieveryherosample.data.User
import com.egiwon.delieveryherosample.data.source.local.db.GithubUserDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class GithubLocalDataSourceImpl(
        private val githubUserDao: GithubUserDao
) : GithubLocalDataSource {

    override fun getLikeUsers(): Flowable<List<User>> = githubUserDao.getLikeUsers()
            .onBackpressureBuffer()
            .subscribeOn(Schedulers.io())

    override fun setLikeUser(user: User): Completable = githubUserDao.setLikeUser(user)
            .subscribeOn(Schedulers.io())

    override fun removeLikeUser(user: User): Completable = githubUserDao.removeLikeUser(user)
            .subscribeOn(Schedulers.io())

    override fun searchLikeUsers(query: String): Flowable<List<User>> =
            githubUserDao.searchLikeUsers(query)
                    .onBackpressureBuffer()
                    .subscribeOn(Schedulers.io())

}