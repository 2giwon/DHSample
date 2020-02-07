package com.egiwon.delieveryherosample.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(

        @PrimaryKey
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