package com.chamwari.tech.xitique.domain.usecases

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.chamwari.tech.xitique.domain.entities.BalanceSummary
import com.chamwari.tech.xitique.domain.entities.Event
import com.chamwari.tech.xitique.domain.repositories.EventsRepository
import com.chamwari.tech.xitique.domain.repositories.FakeEventsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetMonthlyAggregatedEventSummaryUseCaseTest {


    private  lateinit var repository : FakeEventsRepository

    @BeforeEach
    fun setup() {
        repository = FakeEventsRepository()
    }


    @Test
    fun `GIVEN an empty list of events WHEN computing Aggregate Summary THEN throws`() {
        assertFailure {
            GetMonthlyAggregatedEventSummaryUseCase(
                userBalance = 0,
                repository = repository
            ).execute()
        }
    }

    @Test
    fun `GIVEN list with 1 event WHEN computing Aggregate Summary THEN compute correct month summary`() {
        // Arrange
        val timestamp = 1672972800L // 5 de Janeiro de 2024
        val singleEvent = Event(timestamp = timestamp , title = "Olivio birthday")
        val events = listOf(singleEvent)
        repository.addEvents(listOf( singleEvent))

        // ACT
        val monthlyAggregatedSummary = GetMonthlyAggregatedEventSummaryUseCase(userBalance = 0, repository = repository).execute()
        val monthlySummary = monthlyAggregatedSummary.monthSummary

        // ASSERT
        assertThat(monthlySummary.dateOfEvents).isEqualTo(listOf(5))
        assertThat(monthlySummary.month).isEqualTo(0)
        assertThat(monthlySummary.monthPrettyName).isEqualTo("Janeiro")
    }
}
