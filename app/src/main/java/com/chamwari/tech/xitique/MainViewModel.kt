package com.chamwari.tech.xitique

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainState(
    val isLoading: Boolean = false,
    val signedUsers: List<String> = emptyList()
)

class MainViewModel(
    private val repository : MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        MainState()
    )

    val state = _state.asStateFlow()
    init { getEvents() }

    private fun getEvents() {
        viewModelScope.launch {
            repository.getEvents().collect { events ->
                _state.update {
                    it.copy(signedUsers = events.map { it.name })
                }
            }
        }
    }
}