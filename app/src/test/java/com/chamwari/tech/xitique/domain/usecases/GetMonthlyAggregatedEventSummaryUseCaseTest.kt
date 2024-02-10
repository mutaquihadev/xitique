package com.chamwari.tech.xitique.domain.usecases

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.chamwari.tech.xitique.domain.entities.Event
import com.chamwari.tech.xitique.domain.repositories.FakeEventsRepository
import org.junit.jupiter.api.AfterEach
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
        repository.addEvents(events = events)

        // ACT
        val monthlyAggregatedSummary = GetMonthlyAggregatedEventSummaryUseCase(userBalance = 0, repository = repository).execute()
        val monthlySummary = monthlyAggregatedSummary.monthSummary

        // ASSERT
        assertThat(monthlySummary.dateOfEvents).isEqualTo(listOf(5))
        assertThat(monthlySummary.month).isEqualTo(0)
        assertThat(monthlySummary.monthPrettyName).isEqualTo("Janeiro")
    }

    @Test
    fun `GIVEN list with 5 events in February WHEN computing Aggregate Summary THEN compute correct dateOfEvents`() {
        //Arrange
        val timestamp1 = 1706745600L // - Quinta-feira, 1 de fevereiro de 2024 às 00:00
        val timestamp2 = 1707307200L // - Quarta-feira, 7 de fevereiro de 2024 às 12:00
        val timestamp3 = 1707933600L // - Quarta-feira, 14 de fevereiro de 2024 às 18:00
        val timestamp4 = 1708495200L // - Quarta-feira, 21 de fevereiro de 2024 às 06:00
        //val timestamp5 = 1709161200L // - Quarta-feira, 28 de fevereiro de 2024 às 23:00

        val events = listOf(
            Event(timestamp = timestamp1,"Olivio birthday"),
            Event(timestamp = timestamp2,"Bianca birthday"),
            Event(timestamp = timestamp3,"Michael birthday"),
            Event(timestamp = timestamp4,"Binu birthday"),
            //Event(timestamp = timestamp5,"Bells birthday")
        )

        repository.addEvents(events = events)

        //Act
        val monthlyAggregatedEventSummary = GetMonthlyAggregatedEventSummaryUseCase(userBalance = 0, repository = repository).execute()
        val monthSummary = monthlyAggregatedEventSummary.monthSummary

        //Assert
        val expectedDateOfEventsList = listOf(1, 7, 14, 21)
        assertThat(monthSummary.dateOfEvents).isEqualTo(expectedDateOfEventsList)
    }

    @AfterEach
    fun tearDown() {
        repository.clear()
    }
}
