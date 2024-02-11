package com.chamwari.tech.xitique.domain.entities


data class BalanceSummary(
    val relativeBalanceInPercentage: Int,
    val balanceMessage: String,
    val balance : Int
)