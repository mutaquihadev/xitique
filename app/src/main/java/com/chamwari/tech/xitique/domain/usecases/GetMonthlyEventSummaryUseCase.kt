package com.chamwari.tech.xitique.domain.usecases

import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary
import com.chamwari.tech.xitique.domain.repositories.EventCostRepository
import com.chamwari.tech.xitique.domain.repositories.EventsRepository
import com.chamwari.tech.xitique.domain.repositories.UserBalanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class GetMonthlyAggregatedEventSummaryUseCase(
    private val repository: EventsRepository,
    private val computeMonthlyEventSummaryUseCase: ComputeMonthlyEventSummaryUseCase
) {

    fun execute(): Flow<MonthlyAggregatedEventSummary> = flow {

        repository.getEvents().collectLatest { events ->
            if (events.isEmpty()) {
                throw IllegalArgumentException("Empty list of events is not computable")
            }

            val monthlyAggregatedEventSummary = computeMonthlyEventSummaryUseCase.execute()

            emit(monthlyAggregatedEventSummary)
        }
    }
}