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


    private val eventCost : Int = 300
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
                eventCost = eventCost,
                repository = repository
            ).execute()
        }
    }

    @Test
    fun `GIVEN list with 1 event WHEN computing Aggregate Summary THEN compute correct month summary`() {
        // Arrange
        val timestamp = 1705312800L // 15 de Janeiro de 2024
        val singleEvent = Event(timestamp = timestamp , title = "Olivio birthday")
        val events = listOf(singleEvent)
        repository.addEvents(events = events)

        // ACT
        val monthlyAggregatedSummary = GetMonthlyAggregatedEventSummaryUseCase(userBalance = 0, eventCost = eventCost,repository = repository).execute()
        val monthlySummary = monthlyAggregatedSummary.monthSummary

        // ASSERT
        assertThat(monthlySummary.dateOfEvents).isEqualTo(listOf(15))
        assertThat(monthlySummary.month).isEqualTo(1)
        assertThat(monthlySummary.monthPrettyName).isEqualTo("Janeiro")
        assertThat(monthlySummary.totalCost).isEqualTo(300)
    }

    @Test
    fun `GIVEN list with 4 events in February WHEN computing Aggregate Summary THEN compute correct month summary`() {
        //Arrange
        val timestamp1 = 1706745600L // - Quinta-feira, 1 de fevereiro de 2024 às 00:00
        val timestamp2 = 1707307200L // - Quarta-feira, 7 de fevereiro de 2024 às 12:00
        val timestamp3 = 1707933600L // - Quarta-feira, 14 de fevereiro de 2024 às 18:00
        val timestamp4 = 1708495200L // - Quarta-feira, 21 de fevereiro de 2024 às 06:00

        val events = listOf(
            Event(timestamp = timestamp1,"Olivio birthday"),
            Event(timestamp = timestamp2,"Bianca birthday"),
            Event(timestamp = timestamp3,"Michael birthday"),
            Event(timestamp = timestamp4,"Binu birthday"),
        )

        repository.addEvents(events = events)

        //Act
        val monthlyAggregatedEventSummary = GetMonthlyAggregatedEventSummaryUseCase(
            userBalance = 0,
            eventCost = eventCost,
            repository = repository
        ).execute()

        val monthSummary = monthlyAggregatedEventSummary.monthSummary

        //Assert
        val expectedDateOfEventsList = listOf(1, 7, 14, 21)
        assertThat(monthSummary.dateOfEvents).isEqualTo(expectedDateOfEventsList)
        assertThat(monthSummary.month).isEqualTo(2)
        assertThat(monthSummary.monthPrettyName).isEqualTo("Fevereiro")
        assertThat(monthSummary.totalCost).isEqualTo(1200)
    }


    //TODO: test with parameters coming from a file
    @Test
    fun `Given list with 7 events and user balance 0 in March WHEN computing Aggregate Summary then compute correct Month Summary`() {
        //Arrange
        val timestamp1 =  1709287200L // "1 Março 2024, 10:00"
        val timestamp2 =  1709373600L // "2 Março 2024, 10:00"
        val timestamp3 =  1709460000L // "3 Março 2024, 10:00"
        val timestamp4 =  1709546400L // "4 Março 2024, 10:00"
        val timestamp5 =  1709632800L // "5 Março 2024, 10:00"
        val timestamp6 =  1709719200L // "6 Março 2024, 10:00"
        val timestamp7 =  1709805600L // "7 Março 2024, 10:00"

        val events = listOf(
            Event(timestamp = timestamp1,"Olivio birthday"),
            Event(timestamp = timestamp2,"Bianca birthday"),
            Event(timestamp = timestamp3,"Michael birthday"),
            Event(timestamp = timestamp4,"Binu birthday"),
            Event(timestamp = timestamp5,"Ulisses birthday"),
            Event(timestamp = timestamp6,"Bruno birthday"),
            Event(timestamp = timestamp7,"Vinicius birthday"),
        )

        repository.addEvents(events = events)

        //Act
        val monthlyAggregatedEventSummary = GetMonthlyAggregatedEventSummaryUseCase(
            userBalance = 0,
            eventCost = eventCost,
            repository = repository
        ).execute()

        // Assert
        val monthSummary = monthlyAggregatedEventSummary.monthSummary

        //Assert
        val expectedDateOfEventsList = listOf(1, 2, 3, 4, 5, 6, 7)
        assertThat(monthSummary.dateOfEvents).isEqualTo(expectedDateOfEventsList)
        assertThat(monthSummary.month).isEqualTo(3)
        assertThat(monthSummary.monthPrettyName).isEqualTo("Março")
        assertThat(monthSummary.totalCost).isEqualTo(2100)
    }

    //TODO: test with parameters coming from a file
    @Test
    fun `Given list with 7 events and user balance 0 in March WHEN computing Aggregate Summary then compute correct Balance Summary`() {
        //Arrange
        val timestamp1 =  1709287200L // "1 Março 2024, 10:00"
        val timestamp2 =  1709373600L // "2 Março 2024, 10:00"
        val timestamp3 =  1709460000L // "3 Março 2024, 10:00"
        val timestamp4 =  1709546400L // "4 Março 2024, 10:00"
        val timestamp5 =  1709632800L // "5 Março 2024, 10:00"
        val timestamp6 =  1709719200L // "6 Março 2024, 10:00"
        val timestamp7 =  1709805600L // "7 Março 2024, 10:00"

        val events = listOf(
            Event(timestamp = timestamp1,"Olivio birthday"),
            Event(timestamp = timestamp2,"Bianca birthday"),
            Event(timestamp = timestamp3,"Michael birthday"),
            Event(timestamp = timestamp4,"Binu birthday"),
            Event(timestamp = timestamp5,"Ulisses birthday"),
            Event(timestamp = timestamp6,"Bruno birthday"),
            Event(timestamp = timestamp7,"Vinicius birthday"),
        )

        repository.addEvents(events = events)

        //Act
        val monthlyAggregatedEventSummary = GetMonthlyAggregatedEventSummaryUseCase(
            userBalance = 0,
            eventCost = eventCost,
            repository = repository
        ).execute()

        // Assert
        val monthBalanceSummary = monthlyAggregatedEventSummary.monthSummary.monthBalanceSummary

        assertThat(monthBalanceSummary.balance).isEqualTo(0)
        assertThat(monthBalanceSummary.balanceMessage).isEqualTo("Seu saldo mensal é:")
        assertThat(monthBalanceSummary.relativeBalanceInPercentage).isEqualTo(0.0f)
    }


    @AfterEach
    fun tearDown() {
        repository.clear()
    }
}
