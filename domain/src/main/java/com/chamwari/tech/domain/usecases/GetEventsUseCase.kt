package com.chamwari.tech.domain.usecases

import com.chamwari.tech.domain.entities.Event
import com.chamwari.tech.domain.repositories.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date // todo: find a kotlin replacement to support multiplatform

class GetEventsUseCase(
    val currentDate: Date,
    val eventRepository: EventRepository,
    val userBalanceRepository: EventRepository
) {
    inline fun execute(): Flow<List<Event>> = flow {
        emit(emptyList())
    }
}