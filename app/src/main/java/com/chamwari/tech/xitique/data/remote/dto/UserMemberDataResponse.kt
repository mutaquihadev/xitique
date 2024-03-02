package com.chamwari.tech.xitique.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserMemberDataResponse(
    val members: List<UserEntity>
)

