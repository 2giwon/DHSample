package com.egiwon.repository.impl

import com.egiwon.repository.GithubLocalDataSource
import com.egiwon.repository.GithubRemoteDataSource
import com.egiwon.repository.GithubRepository
import com.egiwon.repository.model.UserDomainEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource
) : GithubRepository {

    override fun searchUserInfo(query: String): Single<List<UserDomainEntity>> =
        githubRemoteDataSource.searchGithubUser(query)
            .zipWith(
                githubLocalDataSource.getLikeUsers(),
                BiFunction { response, likeUsers ->
                    response.map { user -> selectedLikeUserFromLocalLikeUser(user, likeUsers) }
                })

    private fun selectedLikeUserFromLocalLikeUser(
        userDomain: UserDomainEntity,
        likeUserDomains: List<UserDomainEntity>
    ): UserDomainEntity =
        likeUserDomains.find { (userDomain.id == it.id) }
            ?.run { UserDomainEntity(id, avatarUrl, name, score, true) }
            ?: userDomain

    override fun addLikeUser(userDomain: UserDomainEntity): Completable =
        githubLocalDataSource.addLikeUser(userDomain)

    override fun removeLikeUser(userDomain: UserDomainEntity): Completable =
        githubLocalDataSource.removeLikeUser(userDomain)

    override fun getLikeUser(): Single<List<UserDomainEntity>> =
        githubLocalDataSource.getLikeUsers()

    override fun searchLikeUsers(query: String): Single<List<UserDomainEntity>> =
        githubLocalDataSource.searchLikeUsers(query)


}