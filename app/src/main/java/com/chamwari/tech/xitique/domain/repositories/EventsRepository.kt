package com.chamwari.tech.xitique.domain.repositories

import com.chamwari.tech.xitique.domain.entities.Event

interface EventsRepository {
    fun getEvents() : List<Event>
}