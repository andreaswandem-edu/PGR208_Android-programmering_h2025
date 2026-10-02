package com.example.eksamen_h2025.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnimeDao {
    @Query("SELECT * FROM AnimeEntity")
    suspend fun getAllAnimes(): List<AnimeEntity>


    @Query("SELECT * FROM AnimeEntity WHERE favorite = 1")
    suspend fun getFavoriteAnime(): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity): Long

    @Query("SELECT * FROM AnimeEntity WHERE id = :animeId")
    suspend fun getAnimeById(animeId: Int): AnimeEntity?

    @Update
    suspend fun updateAnime(anime: AnimeEntity)

    @Delete
    suspend fun deleteAnime(anime: AnimeEntity)
}