package com.egiwon.repository.model

data class UserDomainEntity(
    val id: String = "",
    val avatarUrl: String = "",
    val name: String = "",
    val score: Double = 0.0,
    val like: Boolean = false
) : DomainEntity
