package com.chamwari.tech.xitique.data.remote

import com.chamwari.tech.xitique.data.remote.dto.EventsResponse
import com.chamwari.tech.xitique.data.remote.dto.UsersResponse

interface XitiqueService {
    suspend fun getSignedUsers() : UsersResponse
    suspend fun getEvents() : EventsResponse
}