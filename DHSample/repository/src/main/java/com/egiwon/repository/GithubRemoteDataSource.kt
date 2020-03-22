package com.egiwon.repository

import com.egiwon.repository.model.UserDomainEntity
import io.reactivex.Single

interface GithubRemoteDataSource {
    fun searchGithubUser(query: String): Single<List<UserDomainEntity>>
}