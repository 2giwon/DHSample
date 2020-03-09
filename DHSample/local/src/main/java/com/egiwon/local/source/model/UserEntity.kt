package com.egiwon.local.source.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.egiwon.repository.model.DomainEntity
import com.egiwon.repository.model.UserDomainEntity

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String = "",

    val avatarUrl: String = "",

    val name: String = "",

    val score: Double = 0.0,

    val like: Boolean = false
) : LocalEntity

fun UserEntity.mapperToUser(): DomainEntity =
    UserDomainEntity(
        id = id,
        avatarUrl = avatarUrl,
        name = name,
        score = score,
        like = like
    )