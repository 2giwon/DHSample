package com.egiwon.remote.impl

import com.egiwon.common.wrapper.SchedulersExt.ioThreadSchedulers
import com.egiwon.remote.response.mapToDomainEntity
import com.egiwon.remote.service.GithubSearchLikeService
import com.egiwon.repository.GithubRemoteDataSource
import com.egiwon.repository.model.UserDomainEntity
import io.reactivex.Single

class GithubRemoteDataSourceImpl(
    private val service: GithubSearchLikeService
) : GithubRemoteDataSource {

    override fun searchGithubUser(query: String): Single<List<UserDomainEntity>> =
        service.getUserInfo(query)
            .map { list -> list.remoteUserResponses.map { item -> item.mapToDomainEntity() } }
            .subscribeOn(ioThreadSchedulers)
}