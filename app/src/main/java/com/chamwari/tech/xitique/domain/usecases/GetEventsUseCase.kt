package com.chamwari.tech.xitique.domain.usecases

import com.chamwari.tech.xitique.domain.entities.BalanceSummary
import com.chamwari.tech.xitique.domain.entities.Event
import com.chamwari.tech.xitique.domain.entities.MonthSummary
import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary

class GetMonthlyAggregatedEventSummaryUseCase(
    val userBalance: Int,
    val events : List<Event>
) {
    fun execute(): MonthlyAggregatedEventSummary {
        return MonthlyAggregatedEventSummary(
            monthSummary = MonthSummary(
                month = 0,
                monthBalanceSummary = BalanceSummary(),
                dateOfEvents = emptyList(),
                monthPrettyName = "January"
            ),
            eventsSummary = emptyList())
    }
}