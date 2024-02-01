package com.chamwari.tech.domain.repositories

import com.chamwari.tech.domain.entities.Event

class FakeEventRepository : EventRepository {
    override suspend fun getEvents(): List<Event> {
        return emptyList()
    }
}