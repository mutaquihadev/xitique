package com.chamwari.tech.xitique.domain.entities

data class MonthSummary(
    val month : Int,
    val monthPrettyName: String,
    val dateOfEvents: List<Int>,
    val monthBalanceSummary: BalanceSummary
)