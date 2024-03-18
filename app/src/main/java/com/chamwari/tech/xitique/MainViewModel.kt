package com.chamwari.tech.xitique

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chamwari.tech.xitique.domain.entities.MonthlyAggregatedEventSummary
import com.chamwari.tech.xitique.domain.usecases.GetMonthlyAggregatedEventSummaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainState(
    val isLoading: Boolean = false,
    val signedUsers: List<String> = emptyList()
)

class MainViewModel(
    private val getSummaryUseCase: GetMonthlyAggregatedEventSummaryUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        MainState()
    )

    val state = _state.asStateFlow()
    init { getEvents() }

    private fun getEvents() {
        viewModelScope.launch {
            getSummaryUseCase.execute().collect { summary: MonthlyAggregatedEventSummary ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        signedUsers = summary.eventsSummary.map { it.event.title }
                    )
                }
            }
        }
    }
}