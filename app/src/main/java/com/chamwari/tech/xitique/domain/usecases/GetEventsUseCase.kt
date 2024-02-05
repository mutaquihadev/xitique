package com.chamwari.tech.xitique.domain.usecases

import com.chamwari.tech.xitique.domain.entities.MonthSummary
import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary

class GetMonthlyAggregatedEventSummaryUseCase {
    fun execute(): MonthlyAggregatedEventSummary {
        return MonthlyAggregatedEventSummary(
            monthSummary = MonthSummary(),
            eventsSummary = emptyList())
    }
}