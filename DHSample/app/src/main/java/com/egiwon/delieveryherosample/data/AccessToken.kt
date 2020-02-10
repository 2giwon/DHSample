package com.egiwon.delieveryherosample.data

import com.google.gson.annotations.SerializedName

data class AccessToken(

        @SerializedName("access_token")
        val accessToken: String = "",

        val scope: String = "",

        @SerializedName("token_type")
        val tokenType: String = ""
)

object AccessTokenProvider {
    var token: String = ""
}