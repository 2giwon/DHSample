package com.egiwon.delieveryherosample.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.egiwon.delieveryherosample.data.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    var id: String,

    var avatarUrl: String,

    var name: String,

    var score: Double,

    var like: Boolean = false
)

fun UserEntity.mapperToUser(): User =
    User(
        id = id,
        avatarUrl = avatarUrl,
        name = name,
        score = score,
        like = like
    )