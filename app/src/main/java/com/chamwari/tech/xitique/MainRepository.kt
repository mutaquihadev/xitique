package com.chamwari.tech.xitique

import com.chamwari.tech.xitique.data.remote.dto.UserMemberDataResponse
import com.chamwari.tech.xitique.data.remote.dto.UsersResponse

interface MainRepository {
    suspend fun fetchSignedUsers() : UsersResponse
    suspend fun getUserMemberData(): UserMemberDataResponse
}