package com.example.eksamen_h2025.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import java.sql.SQLException

object AnimeRepository {
    private lateinit var _appDatabase: AppDatabase
    private val _animeDao by lazy { _appDatabase.animeDao() }

    fun initializeDatabase(context: Context) {
        try {
            _appDatabase = Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "anime-database"
            ).build()
        } catch (e: SQLException) {
            Log.e("SqlException", "Initialize database failed: ${e.message}")

        } catch (e: Exception) {
            Log.e("Exception", "Something went wrong: ${e.message}")
        }
    }

    suspend fun getAllAnimes(): List<AnimeEntity> {
        try {
            return _animeDao.getAllAnimes()
        } catch (e: SQLException) {
            Log.e("SQLException", "Failed to fetch all animes: ${e.message}")
            return emptyList()

        } catch (e: Exception) {
            Log.e("Exception", "Something went wrong ${e.message}")
            return emptyList()
        }
    }

    suspend fun insertAnime(anime: AnimeEntity): Long {
        try {
            return _animeDao.insertAnime(anime)
        } catch (e: SQLException) {
            Log.e("AnimeRepository", "Insert failed: ${e.message}")
            return -1L

        } catch (e: Exception) {
            Log.e("Exception", "Something went wrong: ${e.message}")
            return -1L
        }
    }

    suspend fun updateAnime(anime: AnimeEntity) {
        try {
            _animeDao.updateAnime(anime)
        } catch (e: SQLException) {
            Log.e("SQLException", "Update failed: ${e.message}")

        } catch (e: Exception) {
            Log.e("Exception", "Something went wrong: ${e.message}")
        }
    }

    suspend fun getAnimeById(animeId: Int): AnimeEntity? {
        try {
            return _animeDao.getAnimeById(animeId)
        } catch (e: SQLException) {
            Log.e("SQLException", "Fetch by ID failed: ${e.message}")
            return null

        } catch (e: Exception) {
            Log.e("Exception", "Something went wrong: ${e.message}")
            return null
        }
    }

    suspend fun deleteAnime(anime: AnimeEntity) {
        try {
            _animeDao.deleteAnime(anime)
        } catch (e: SQLException) {
            Log.e("SQLException", "Delete failed: ${e.message}")

        } catch (e: Exception) {
            Log.e("Exception", "Something went wrong: ${e.message}")
        }
    }
}