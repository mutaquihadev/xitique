package com.chamwari.tech.xitique.domain.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateUtils {
    val monthsInPortuguese = listOf(
    "Janeiro",
    "Fevereiro",
    "Março",
    "Abril",
    "Maio",
    "Junho",
    "Julho",
    "Agosto",
    "Setembro",
    "Outubro",
    "Novembro",
    "Dezembro"
    )

    fun timestampToLocalDateTime(timestampInSeconds: Long): LocalDateTime {
        val instant = Instant.fromEpochSeconds(timestampInSeconds)
        val timeZone = TimeZone.currentSystemDefault()

        return instant.toLocalDateTime(timeZone)
    }
}