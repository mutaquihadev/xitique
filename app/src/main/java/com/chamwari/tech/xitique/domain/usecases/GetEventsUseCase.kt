package com.chamwari.tech.xitique.domain.usecases

import com.chamwari.tech.xitique.domain.entities.BalanceSummary
import com.chamwari.tech.xitique.domain.entities.MonthSummary
import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary
import com.chamwari.tech.xitique.domain.repositories.EventsRepository
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetMonthlyAggregatedEventSummaryUseCase(
    private val userBalance: Int,
    private val eventCost: Int,
    private val repository: EventsRepository
) {

    private val monthsInPortuguese = listOf(
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        )

    fun execute(): MonthlyAggregatedEventSummary {

        val events = repository.getEvents()

        if (events.isEmpty()) {
            throw IllegalArgumentException("Empty list of events is not computable")
        }

        val timestamps = events.map { it.timestamp }
        val dateTimes = timestamps.map { timestampSeconds ->
            val instant = Instant.fromEpochSeconds(timestampSeconds)
            val timeZone = TimeZone.currentSystemDefault()

            // Convert to LocalDateTime for specific time zone
            val localDateTime = instant.toLocalDateTime(timeZone)

            // For displaying the date in a specific format, you would still format it manually or use a platform-specific formatter
            println(localDateTime)

            //extractListOfDates(events)
            localDateTime
        }

        val monthNumber = dateTimes.first().month.value
        print("Month is $monthNumber")
        val dateOfEvents = dateTimes.map { it.dayOfMonth }
        val monthPrettyName = monthsInPortuguese[monthNumber - 1]
        return MonthlyAggregatedEventSummary(
            monthSummary = MonthSummary(
                month = monthNumber,
                monthBalanceSummary = BalanceSummary(
                    balance = userBalance,
                    balanceMessage = "",
                     relativeBalanceInPercentage = 0.0f
                ),
                dateOfEvents = dateOfEvents,
                monthPrettyName = monthPrettyName,
                totalCost = eventCost * events.size
            ),
            eventsSummary = emptyList()
        )
    }
}