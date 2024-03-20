package com.chamwari.tech.xitique.presentation

import com.chamwari.tech.xitique.data.db.EventDAO
import com.chamwari.tech.xitique.data.db.EventEntity
import com.chamwari.tech.xitique.data.db.toEvent
import com.chamwari.tech.xitique.data.remote.XitiqueService
import com.chamwari.tech.xitique.data.remote.dto.toEventEntity
import com.chamwari.tech.xitique.domain.entities.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class EventSummaryRepositoryImpl(
    private val service: XitiqueService,
    private val eventDAO: EventDAO
) : EventSumaryRepository {
    override  fun getEvents(): Flow<List<Event>> {
        return eventDAO.getEvents().map{
            it.map { eventEntity -> eventEntity.toEvent() }
        }.onEach { events ->
            events.ifEmpty {
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