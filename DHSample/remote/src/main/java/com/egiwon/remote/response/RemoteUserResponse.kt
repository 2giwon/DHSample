package com.egiwon.remote.response

import com.egiwon.repository.model.UserDomainEntity
import com.google.gson.annotations.SerializedName

data class RemoteUserResponse(

    @SerializedName("id")
    val id: String = "",

    @SerializedName("avatarUrl")
    val avatarUrl: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("score")
    val score: Double = 0.0,

    @SerializedName("like")
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