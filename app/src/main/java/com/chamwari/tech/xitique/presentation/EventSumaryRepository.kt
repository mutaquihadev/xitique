package com.chamwari.tech.xitique.presentation

import com.chamwari.tech.xitique.domain.entities.Event
import kotlinx.coroutines.flow.Flow

interface EventSumaryRepository {
    fun getEvents(): Flow<List<Event>>
}