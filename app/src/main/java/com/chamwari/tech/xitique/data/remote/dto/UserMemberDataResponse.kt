package com.chamwari.tech.xitique.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserMemberDataResponse(
    val members: List<UserEntity>
)

@Serializable
data class UserEntity(
    val name: String,
    val birthDay: Long,
    val balance: Int,
    val phoneNumber: String,
    val isAdmin: Boolean,
    val isDebug: Boolean = false
)