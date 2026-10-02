package com.example.eksamen_h2025.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AnimeEntity::class],
    version = 2,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}
