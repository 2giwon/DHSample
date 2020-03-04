package com.egiwon.delieveryherosample.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.egiwon.delieveryherosample.data.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String = "",

    val avatarUrl: String = "",

    val name: String = "",

    val score: Double = 0.0,

    val like: Boolean = false
)

fun UserEntity.mapperToUser(): User =
    User(
        id = id,
        avatarUrl = avatarUrl,
        name = name,
        score = score,
        like = like
    )