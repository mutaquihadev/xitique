package com.chamwari.tech.domain.usecases

import org.junit.jupiter.api.Test


class ComputeMonthSummaryUseCaseTest {

    private lateinit var useCase: ComputeMonthSummaryUseCase

//    @Test
//    fun `Given empty list of events WHEN computing monthly month summary THEN should throw an error`() {
//        useCase = ComputeMonthSummaryUseCase(emptyList())
//
//        Assertions.assertThrows(IllegalArgumentException::class.java) {
//            useCase.execute()
//        }
//    }


    fun `test is test`() {
        assert(true)
    }


    fun `adding two numbers`() {
        assert(4* 4 == 16)
    }


    @Test
    fun `multiply two numbers`() {
        assert(5+ 5 == 11)
    }

}