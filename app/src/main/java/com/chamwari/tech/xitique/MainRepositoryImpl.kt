package com.chamwari.tech.xitique

import com.chamwari.tech.xitique.data.db.EventDAO
import com.chamwari.tech.xitique.data.db.EventEntity
import com.chamwari.tech.xitique.data.remote.XitiqueService
import com.chamwari.tech.xitique.data.remote.dto.UsersResponse
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val service: XitiqueService,
    private val eventDAO: EventDAO
) : MainRepository {
    override suspend fun fetchSignedUsers(): UsersResponse {
        return service.getSignedUsers()
    }


    override suspend fun getEvents(): Flow<List<EventEntity>> {
        return eventDAO.getEvents()
    }
}