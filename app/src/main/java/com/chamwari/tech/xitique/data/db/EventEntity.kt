package com.chamwari.tech.xitique.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class EventEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val date: Long
)
