package com.chamwari.tech.xitique.data.db

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface EventDAO {
    @Query("SELECT * FROM evententity")
    fun getEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM evententity WHERE id = :id")
    fun getEvent(id: String): Flow<EventEntity>

    @Query("DELETE FROM evententity")
    suspend fun deleteAllEvents()

    @Query("DELETE FROM evententity WHERE id = :id")
    suspend fun deleteEvent(id: String)

    @Query("DELETE FROM evententity WHERE id IN (:ids)")
    suspend fun deleteEvents(ids: List<String>)
}