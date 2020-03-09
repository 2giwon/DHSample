package com.egiwon.delieveryherosample.ui.model

import com.egiwon.repository.model.UserDomainEntity

data class User(
    val id: String = "",
    val avatarUrl: String = "",
    val name: String = "",
    val score: Double = 0.0,
    val like: Boolean = false
)

fun UserDomainEntity.mapToUser(): User {
    return User(
        id = id,
        avatarUrl = avatarUrl,
        name = name,
        score = score,
        like = like
    )
}

fun User.mapToDomainUser(): UserDomainEntity {
    return UserDomainEntity(
        id = id,
        avatarUrl = avatarUrl,
        name = name,
        score = score,
        like = like
    )
}