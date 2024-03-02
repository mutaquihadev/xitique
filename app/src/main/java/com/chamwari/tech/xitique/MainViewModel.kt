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

    fun getMembers() {
        viewModelScope.launch {
            val response = repository.getUserMemberData()
            val names = response.members.map { it.name }
            println("NAMES = ${names.take(4)} size = ${names.size}")
        }
    }

    fun getEvents() {
        viewModelScope.launch {
            val response = repository.getEvents()

            println("NAMES = ${response.events.take(4)} size = ${response.events.size}")
        }
    }
}