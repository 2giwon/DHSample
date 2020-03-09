package com.egiwon.remote.response

import com.egiwon.repository.model.UserDomainEntity

data class RemoteUserResponse(
    val id: String = "",
    val avatarUrl: String = "",
    val name: String = "",
    val score: Double = 0.0,
    val like: Boolean = false
) : RemoteResponse

fun RemoteUserResponse.mapToDomainEntity(): UserDomainEntity {
    return UserDomainEntity(
        id = id,
        avatarUrl = avatarUrl,
        name = name,
        score = score,
        like = like
    )
}