package com.chamwari.tech.xitique.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDAO {
    @Query("SELECT * FROM evententity")
    fun getEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM evententity WHERE date BETWEEN :startDate AND :endDate")
    fun getEvents(startDate: Long, endDate: Long): Flow<List<EventEntity>>
    @Query("SELECT * FROM evententity WHERE id = :id")
    fun getEvent(id: String): Flow<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(eventEntity: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEvents(events: List<EventEntity>)
    @Query("UPDATE evententity SET name = :name, date = :date WHERE id = :id")
    suspend fun updateEvent(id: String, name: String, date: Long)

    @Query("DELETE FROM evententity")
    suspend fun deleteAllEvents()

    @Query("DELETE FROM evententity WHERE id = :id")
    suspend fun deleteEvent(id: String)

    @Query("DELETE FROM evententity WHERE id IN (:ids)")
    suspend fun deleteEvents(ids: List<String>)
}