package com.chamwari.tech.xitique.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EventDTO (
    val id: String,
    val name: String,
    val date: Long
)