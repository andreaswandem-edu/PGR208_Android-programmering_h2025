package com.example.eksamen_h2025.screens.animedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen_h2025.data.api.Anime
import com.example.eksamen_h2025.data.api.JikanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeDetailsViewModel : ViewModel() {
    private val _anime = MutableStateFlow<Anime?>(null)

    val anime = _anime.asStateFlow()

    fun setAnime(animeId: Int) {
        viewModelScope.launch {
            val result = JikanRepository.getAnimeById(animeId)
            _anime.value = result
        }
    }
}