package com.chamwari.tech.xitique

import com.chamwari.tech.xitique.data.db.EventEntity
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getEvents(): Flow<List<EventEntity>>
}