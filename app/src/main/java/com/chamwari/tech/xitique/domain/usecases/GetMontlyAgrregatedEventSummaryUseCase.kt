package com.chamwari.tech.xitique.domain.usecases

import com.chamwari.tech.xitique.domain.entities.BalanceSummary
import com.chamwari.tech.xitique.domain.entities.Event
import com.chamwari.tech.xitique.domain.entities.EventSummary
import com.chamwari.tech.xitique.domain.entities.MonthSummary
import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary
import com.chamwari.tech.xitique.domain.repositories.EventsRepository
import com.chamwari.tech.xitique.domain.utils.DateUtils
import kotlinx.datetime.LocalDateTime
import kotlin.math.roundToInt

class GetMonthlyAggregatedEventSummaryUseCase(
    private val userBalance: Int,
    private val eventCost: Int,
    private val repository: EventsRepository
) {

    fun execute(): MonthlyAggregatedEventSummary {

        val events = repository.getEvents()

        if (events.isEmpty()) {
            throw IllegalArgumentException("Empty list of events is not computable")
        }

        val localDateTimesList: List<LocalDateTime> = events.map {
            val timestamp = it.timestamp
            DateUtils.timestampToLocalDateTime(timestamp)
        }

        val relativeBalanceInPercentage = calculateRelativeBalance(events)

        val monthNumber = localDateTimesList.first().month.value
        val dateOfEvents = localDateTimesList.map { it.dayOfMonth }
        val monthPrettyName = DateUtils.monthsInPortuguese[monthNumber - 1]

        val monthEventsTotalCost = eventCost * events.size
        val montBalanceSummary = BalanceSummary(
            balance = userBalance, relativeBalanceInPercentage = relativeBalanceInPercentage
        )

        val eventSummaryList = computeEventsSummary(events)


        return MonthlyAggregatedEventSummary(
            monthSummary = MonthSummary(
                month = monthNumber,
                monthBalanceSummary = montBalanceSummary,
                dateOfEvents = dateOfEvents,
                monthPrettyName = monthPrettyName,
                totalCost = monthEventsTotalCost
            ), eventsSummary = eventSummaryList
        )
    }

    private fun calculateRelativeBalance(events: List<Event>): Int {
        val balanceRatio = (userBalance * 1f / (eventCost * events.size)) * 100
        val balance = balanceRatio.roundToInt()

        return when {
            balance < 0 -> 0
            balance > 100 -> 100
            else -> balance
        }
    }

    private fun computeEventsSummary(events: List<Event>): List<EventSummary> {

        return events.mapIndexed { index, event ->
            val balanceAfterEvent = userBalance - (index) * eventCost
            val ratio = when {
                 balanceAfterEvent > eventCost -> 100
                 balanceAfterEvent <= 0 -> 0
                 else -> ((balanceAfterEvent * 1f / eventCost) * 100).roundToInt()
            }

            EventSummary(
                event = event,
                eventSummary = BalanceSummary(
                    balanceTitle = "Seu saldo para o evento",
                    balance = eventCost,
                    relativeBalanceInPercentage = ratio
                )
            )
        }
    }
}