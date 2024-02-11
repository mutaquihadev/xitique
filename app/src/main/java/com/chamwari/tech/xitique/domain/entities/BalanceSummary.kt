package com.chamwari.tech.xitique.domain.entities


data class BalanceSummary(
    val relativeBalanceInPercentage: Float,
    val balanceMessage: String,
    val balance : Int
)