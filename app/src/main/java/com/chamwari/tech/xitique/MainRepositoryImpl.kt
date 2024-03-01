package com.chamwari.tech.xitique

import com.chamwari.tech.xitique.data.remote.XitiqueService
import com.chamwari.tech.xitique.data.remote.dto.UserMemberDataResponse
import com.chamwari.tech.xitique.data.remote.dto.UsersResponse

class MainRepositoryImpl(
    private val service: XitiqueService
) : MainRepository {
    override suspend fun fetchSignedUsers(): UsersResponse {
        return service.getSignedUsers()
    }

    override suspend fun getUserMemberData(): UserMemberDataResponse {
        return service.getEvents()
    }
}