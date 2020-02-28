package com.egiwon.delieveryherosample.data

import com.google.gson.annotations.SerializedName

data class UserLikeResponse(
    @SerializedName("items")
    val users: List<User>
)