package com.chamwari.tech.xitique.domain.entities

data class MonthlyAggregatedEventSummary (
    val monthSummary : MonthSummary,
    val eventsSummary: List<EventSummary>
)