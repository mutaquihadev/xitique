package com.chamwari.tech.xitique.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chamwari.tech.xitique.domain.entities.Event


//TODO Convert date into pretty date
//extract month and year and add on entity
// to facilitate easy query
@Entity
class EventEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val date: Long
)

fun EventEntity.toEvent() = Event(
    timestamp = date,
    title = name
)

