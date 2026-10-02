package com.example.eksamen_h2025.screens.animeedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen_h2025.data.room.AnimeEntity
import com.example.eksamen_h2025.data.room.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeEditViewModel : ViewModel() {
    private val _anime = MutableStateFlow<AnimeEntity?>(null)
    val anime = _anime.asStateFlow()

    fun loadAnime(animeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = AnimeRepository.getAnimeById(animeId)
            _anime.value = entity
        }
    }

    fun updateAnime(anime: AnimeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            AnimeRepository.updateAnime(anime)
            _anime.value = anime
        }
    }

    fun deleteAnime(anime: AnimeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            AnimeRepository.deleteAnime(anime)
            _anime.value = null
        }
    }
}
