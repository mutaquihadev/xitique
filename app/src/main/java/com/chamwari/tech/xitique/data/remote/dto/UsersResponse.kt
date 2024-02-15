package com.chamwari.tech.xitique.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse (
    val signedUsers : List<String>
)
