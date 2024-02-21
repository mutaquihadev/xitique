package com.chamwari.tech.xitique.domain.repositories

import com.chamwari.tech.xitique.domain.entities.Event
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun getEvents() : Flow<List<Event>>
}