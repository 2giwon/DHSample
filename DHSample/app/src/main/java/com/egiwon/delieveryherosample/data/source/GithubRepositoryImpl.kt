package com.egiwon.delieveryherosample.data.source

import com.egiwon.delieveryherosample.data.User
import com.egiwon.delieveryherosample.data.UserLikeResponse
import com.egiwon.delieveryherosample.data.source.local.GithubLocalDataSource
import com.egiwon.delieveryherosample.data.source.remote.GithubRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource
) : GithubRepository {

    override fun searchUserInfo(query: String): Single<UserLikeResponse> =
        githubRemoteDataSource.searchGithubUser(query)
            .zipWith(
                githubLocalDataSource.getLikeUsers(),
                BiFunction { response, likeUsers ->
                    UserLikeResponse(response.users.map { user ->
                        selectedLikeUserFromLocalLikeUser(user, likeUsers)
                    })
                })

    private fun selectedLikeUserFromLocalLikeUser(user: User, likeUsers: List<User>): User =
        likeUsers.find { (user.id == it.id) }
            ?.run { User(id, avatarUrl, name, score, true) }
            ?: user

    override fun setLikeUser(user: User): Completable =
        githubLocalDataSource.setLikeUser(user)

    override fun removeLikeUser(user: User): Completable =
        githubLocalDataSource.removeLikeUser(user)

    override fun getLikeUser(): Single<List<User>> =
        githubLocalDataSource.getLikeUsers()

    override fun searchLikeUsers(query: String): Single<List<User>> =
        githubLocalDataSource.searchLikeUsers(query)


}