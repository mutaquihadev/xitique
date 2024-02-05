package com.chamwari.tech.xitique.domain.entities
data class EventSummary (
    val event: Event,
    val currentBalanceSummary: BalanceSummary,
    val balanceAfterEventSummary: BalanceSummary?,
    val balanceAfterMultipleEventsSummary: BalanceSummary?
)