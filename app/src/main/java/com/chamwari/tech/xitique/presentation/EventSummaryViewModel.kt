package com.chamwari.tech.xitique.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary
import com.chamwari.tech.xitique.domain.usecases.GetMonthlyAggregatedEventSummaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EventSummaryState(
    val isLoading: Boolean = false,
    val signedUsers: List<String> = emptyList()
)

class EventSummaryViewModel(
    private val getSummaryUseCase: GetMonthlyAggregatedEventSummaryUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        EventSummaryState()
    )

    val state = _state.asStateFlow()
    init { getEvents() }

    private fun getEvents() {
        viewModelScope.launch {
            getSummaryUseCase.execute().collect { summary: MonthlyAggregatedEventSummary ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        signedUsers = summary.eventsSummary.map { summary -> summary.event.title }
                    )
                }
            }
        }
    }
}