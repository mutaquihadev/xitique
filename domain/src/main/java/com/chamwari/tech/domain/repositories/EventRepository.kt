package com.chamwari.tech.domain.repositories

import com.chamwari.tech.domain.entities.Event

interface EventRepository {
    suspend fun getEvents() : List<Event>
}