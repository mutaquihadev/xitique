package com.chamwari.tech.domain.usecases

import com.chamwari.tech.domain.entities.Event
import com.chamwari.tech.domain.entities.MonthSummary

internal class ComputeMonthSummaryUseCase(
    events: List<Event>
) {

    fun execute(): List<MonthSummary>{
        return emptyList()
    }
}