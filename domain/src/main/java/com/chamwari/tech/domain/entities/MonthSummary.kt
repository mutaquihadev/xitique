package com.chamwari.tech.domain.entities

data class MonthSummary(
    val month: Int,
    val monthPrettyName: String,
    val dateOfEvents: List<String>,
    val totalCost: Int
)