package com.egiwon.delieveryherosample.data

data class User(
    val id: String = "",
    val avatarUrl: String = "",
    val name: String = "",
    val score: Double = 0.0,
    val like: Boolean = false
)