package com.chamwari.tech.xitique

import com.chamwari.tech.xitique.data.db.EventDAO
import com.chamwari.tech.xitique.data.db.EventEntity
import com.chamwari.tech.xitique.data.remote.XitiqueService
import com.chamwari.tech.xitique.data.remote.dto.toEventEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MainRepositoryImpl(
    private val service: XitiqueService,
    private val eventDAO: EventDAO
) : MainRepository {
    override  fun getEvents(): Flow<List<EventEntity>> {
        return eventDAO.getEvents().onEach {
            it.ifEmpty {
                refreshEvents()
            }
        }
    }

    private suspend fun refreshEvents() {
        val events = service.getEvents().events
        val eventEntities: List<EventEntity> = events.map { it.toEventEntity() }
        eventDAO.insertAllEvents(eventEntities)
    }
}