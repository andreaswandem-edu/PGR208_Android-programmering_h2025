package com.example.eksamen_h2025.screens.animecreate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen_h2025.data.room.AnimeEntity
import com.example.eksamen_h2025.data.room.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeCreateViewModel : ViewModel() {
    private val _animeEntities = MutableStateFlow<List<AnimeEntity>>(emptyList())
    val animeEntities = _animeEntities.asStateFlow()

    fun setAnimes() {
        viewModelScope.launch(Dispatchers.IO) {
            _animeEntities.value = AnimeRepository.getAllAnimes()
        }
    }

    init {
        setAnimes()
    }

    fun insertAnime(newAnime: AnimeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val newAnimeId = AnimeRepository.insertAnime(newAnime)

            if (newAnimeId != -1L) {
                val newAnimeEntity = newAnime.copy(id = newAnimeId.toInt())
                _animeEntities.value += newAnimeEntity
            }
        }
    }

    fun setFavoriteStatus(anime: AnimeEntity, favorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updatedAnime = anime.copy(favorite = favorite)
                AnimeRepository.updateAnime(updatedAnime)

                setAnimes()
            } catch (e: Exception) {
                Log.e("AnimeCreateViewModel", "Failed to update favorite status: ${e.message}")
            }
        }
    }
}