package com.egiwon.delieveryherosample.data.source

import com.egiwon.delieveryherosample.data.User
import com.egiwon.delieveryherosample.data.UserLikeResponse
import com.egiwon.delieveryherosample.data.source.local.GithubLocalDataSource
import com.egiwon.delieveryherosample.data.source.remote.GithubRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

class GithubRepositoryImpl(
        private val githubRemoteDataSource: GithubRemoteDataSource,
        private val githubLocalDataSource: GithubLocalDataSource
) : GithubRepository {

    override fun searchUserInfo(query: String): Flowable<UserLikeResponse> =
            githubRemoteDataSource.searchGithubUser(query)
                    .zipWith(
                            githubLocalDataSource.getLikeUsers(),
                            BiFunction { userResponse, likeUsers ->
                                UserLikeResponse(
                                        userResponse.users.map { user ->
                                            likeUsers.find {
                                                user.like = (user.id == it.id)
                                                user.like
                                            } ?: user
                                        }
                                )
                            })

    override fun setLikeUser(user: User): Completable =
            githubLocalDataSource.setLikeUser(user)

    override fun removeLikeUser(user: User): Completable =
            githubLocalDataSource.removeLikeUser(user)

    override fun getLikeUser(): Flowable<List<User>> =
            githubLocalDataSource.getLikeUsers()

    override fun searchLikeUsers(query: String): Flowable<List<User>> =
            githubLocalDataSource.searchLikeUsers(query)

}