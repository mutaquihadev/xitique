package com.chamwari.tech.xitique.domain.usecases

import assertk.assertFailure
import assertk.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetMonthlyAggregatedEventSummaryUseCaseTest {


    @Test
    fun `GIVEN a empty list of events WHEN computing Aggregate Summary THEN throws`() {



        assertFailure {
            GetMonthlyAggregatedEventSummaryUseCase(
                userBalance = 0,
                events = emptyList()
            ).execute()
        }
    }

}
