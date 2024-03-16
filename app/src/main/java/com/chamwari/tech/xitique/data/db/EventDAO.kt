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
    suspend fun insertAllEvents(events: List<EventEntity>)

    @Query("DELETE FROM evententity")
    suspend fun deleteAllEvents()
}