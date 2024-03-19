package com.chamwari.tech.xitique

import com.chamwari.tech.xitique.data.db.EventEntity
import com.chamwari.tech.xitique.domain.entities.Event
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getEvents(): Flow<List<Event>>
}