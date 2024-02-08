package com.chamwari.tech.xitique.domain.repositories

import com.chamwari.tech.xitique.domain.entities.Event

class FakeEventsRepository : EventsRepository{


    private var _events  = mutableListOf<Event>()

    fun addEvents(events: List<Event>) {
        _events.addAll(events)
    }
    override fun getEvents(): List<Event> {
        return _events
    }
}