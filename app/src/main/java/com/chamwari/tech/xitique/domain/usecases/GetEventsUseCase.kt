package com.chamwari.tech.xitique.domain.usecases

import com.chamwari.tech.xitique.domain.entities.BalanceSummary
import com.chamwari.tech.xitique.domain.entities.Event
import com.chamwari.tech.xitique.domain.entities.MonthSummary
import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary
import com.chamwari.tech.xitique.domain.repositories.EventsRepository
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetMonthlyAggregatedEventSummaryUseCase(
    private val userBalance: Int,
    private val repository: EventsRepository
) {
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

        val dateOfEvents = dateTimes.map { it.dayOfMonth }

        return MonthlyAggregatedEventSummary(
            monthSummary = MonthSummary(
                month = 0,
                monthBalanceSummary = BalanceSummary(),
                dateOfEvents = dateOfEvents,
                monthPrettyName = "Janeiro"
            ),
            eventsSummary = emptyList()
        )
    }
}