package com.chamwari.tech.xitique.domain.entities


data class BalanceSummary(
    /**
     * TODO: Implementar regra para mostrar mensagem de summary dependendo do saldo mensal:
     * - Parabéns, seu saldo cobre todos os eventos do mês (verde)
     * - Parabéns, seu saldo cobre os próximos x eventos (verde). Para cobrir o mês deposite mais x dinheiros (em amarelo)
     * - Atenção, você precisa depositar x dinheiros (amarelo), para cobrir os x eventos (amarelo) do mês
     * - Emergência, você está em dívida de x dinheiros (vermelho) para cobrir os x eventos (vermelho) que passaram ou ocorreram.
     *
     */
    val balanceMessage: String = "",
    val relativePercentageTitle: String = "Saldo atual cobre",
    val relativeBalanceInPercentage: Int,
    val balance : Int,
    val balanceTitle: String = "Saldo do mês"
)