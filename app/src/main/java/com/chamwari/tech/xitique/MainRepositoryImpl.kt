package com.chamwari.tech.xitique

import com.chamwari.tech.xitique.data.db.EventDAO
import com.chamwari.tech.xitique.data.db.EventEntity
import com.chamwari.tech.xitique.data.remote.XitiqueService
import com.chamwari.tech.xitique.data.remote.dto.EventsResponse
import com.chamwari.tech.xitique.data.remote.dto.UserMemberDataResponse
import com.chamwari.tech.xitique.data.remote.dto.UsersResponse

class MainRepositoryImpl(
    private val service: XitiqueService,
    private val eventDAO: EventDAO
) : MainRepository {
    override suspend fun fetchSignedUsers(): UsersResponse {
        return service.getSignedUsers()
    }

    override suspend fun getUserMemberData(): UserMemberDataResponse {
        return service.getEvents()
    }

    override suspend fun getEvents(): EventsResponse {

        service.getEvents2().events.forEach {
           val ent =  EventEntity(
                id = it.id,
                name = it.name,
                date = it.date
            )
            eventDAO.insertEvent(ent)
        }

        return service.getEvents2()
    }
}