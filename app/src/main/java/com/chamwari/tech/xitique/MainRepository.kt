package com.chamwari.tech.xitique

import com.chamwari.tech.xitique.data.db.EventEntity
import com.chamwari.tech.xitique.data.remote.dto.UsersResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun fetchSignedUsers() : UsersResponse
    suspend fun getEvents(): Flow<List<EventEntity>>
}