package com.egiwon.delieveryherosample.data

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("id")
    var id: String,

    @field:SerializedName("avatar_url")
    var avatarUrl: String,

    @field:SerializedName("login")
    var name: String,

    @field:SerializedName("score")
    var score: Double,

    @field:SerializedName("like")
    var like: Boolean = false
)