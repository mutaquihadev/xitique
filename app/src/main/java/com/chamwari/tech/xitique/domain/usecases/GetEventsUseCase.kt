package com.chamwari.tech.xitique.domain.usecases

import com.chamwari.tech.xitique.domain.entities.BalanceSummary
import com.chamwari.tech.xitique.domain.entities.MonthSummary
import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary
import com.chamwari.tech.xitique.domain.repositories.EventsRepository

class GetMonthlyAggregatedEventSummaryUseCase(
    private val userBalance: Int,
    private val repository: EventsRepository
) {
    fun execute(): MonthlyAggregatedEventSummary {

        if(repository.getEvents().isEmpty()) {
            throw IllegalArgumentException("Empty list of events is not computable")
        }

        return MonthlyAggregatedEventSummary(
            monthSummary = MonthSummary(
                month = 0,
                monthBalanceSummary = BalanceSummary(),
                dateOfEvents = listOf(5),
                monthPrettyName = "Janeiro"
            ),
            eventsSummary = emptyList())
    }
}