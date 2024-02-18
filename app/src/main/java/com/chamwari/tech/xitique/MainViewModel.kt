package com.chamwari.tech.xitique

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository : MainRepository
) : ViewModel() {
    fun fetchUsers() {
        viewModelScope.launch {
            val usersResponse = repository.fetchSignedUsers()
            println("signed users list RESULT = ${usersResponse.signedUsers}")
        }
    }
}