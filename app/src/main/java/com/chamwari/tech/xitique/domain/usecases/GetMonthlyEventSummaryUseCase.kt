package com.chamwari.tech.xitique.domain.usecases

import com.chamwari.tech.xitique.MainRepository
import com.chamwari.tech.xitique.data.db.toEvent
import com.chamwari.tech.xitique.domain.entities.BalanceSummary
import com.chamwari.tech.xitique.domain.entities.Event
import com.chamwari.tech.xitique.domain.entities.EventSummary
import com.chamwari.tech.xitique.domain.entities.MonthSummary
import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary
import com.chamwari.tech.xitique.domain.repositories.EventsRepository
import com.chamwari.tech.xitique.domain.utils.DateUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.datetime.LocalDateTime
import kotlin.math.roundToInt

class GetMonthlyAggregatedEventSummaryUseCase(
    private val repository: MainRepository,
    private val userBalance: Int = 0,
    private val eventCost: Int = 300,
) {

    fun execute(): Flow<MonthlyAggregatedEventSummary> = repository.getEvents().map { eventEntities ->
            val events = eventEntities.map { it.toEvent() }


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

            val monthlyAggregatedEventSummary =
                MonthlyAggregatedEventSummary(
                    monthSummary = MonthSummary(
                        month = monthNumber,
                        monthBalanceSummary = montBalanceSummary,
                        dateOfEvents = dateOfEvents,
                        monthPrettyName = monthPrettyName,
                        totalCost = monthEventsTotalCost
                    ), eventsSummary = eventSummaryList
                )

            monthlyAggregatedEventSummary
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