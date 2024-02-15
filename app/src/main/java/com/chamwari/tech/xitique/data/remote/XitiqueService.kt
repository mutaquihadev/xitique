package com.chamwari.tech.xitique.data.remote

import com.chamwari.tech.xitique.data.remote.dto.UsersResponse

interface XitiqueService {
    suspend fun getSignedUsers() : UsersResponse
}