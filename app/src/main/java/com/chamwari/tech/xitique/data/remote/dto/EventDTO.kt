package com.chamwari.tech.xitique.data.remote.dto

import com.chamwari.tech.xitique.data.db.EventEntity
import kotlinx.serialization.Serializable

@Serializable
data class EventDTO (
    val id: String,
    val name: String,
    val date: Long
)

fun EventDTO.toEventEntity() = EventEntity(
    id = id,
    name = name,
    date = date
)