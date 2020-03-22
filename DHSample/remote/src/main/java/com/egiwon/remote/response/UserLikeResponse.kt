package com.egiwon.remote.response

import com.google.gson.annotations.SerializedName

data class UserLikeResponse(
    @SerializedName("items")
    val remoteUserResponses: List<RemoteUserResponse>
)