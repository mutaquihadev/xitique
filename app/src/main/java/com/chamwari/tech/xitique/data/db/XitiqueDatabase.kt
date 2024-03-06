package com.chamwari.tech.xitique.data.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [EventEntity::class],
    version = 1,
    exportSchema = true
)
abstract class XitiqueDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDAO
}
