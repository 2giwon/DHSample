package com.egiwon.delieveryherosample.data

data class User(
    val id: String,
    val avatarUrl: String,
    val name: String,
    val score: Double,
    val like: Boolean = false
)