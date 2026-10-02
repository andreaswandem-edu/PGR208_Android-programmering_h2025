package com.example.eksamen_h2025.screens.animesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen_h2025.data.api.Anime
import com.example.eksamen_h2025.data.api.JikanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeSearchViewModel : ViewModel() {

    // Id
    private val _idResult = MutableStateFlow<Anime?>(null)
    val idResult = _idResult.asStateFlow()

    // Name
    private val _nameResult = MutableStateFlow<List<Anime>>(emptyList())
    val nameResult = _nameResult.asStateFlow()

    // Håndtering av id-search
    fun searchById(animeId: Int) {
        viewModelScope.launch {
            _nameResult.value = emptyList()
            _idResult.value = JikanRepository.getAnimeById(animeId)
        }
    }

    // Håndtering av name-search
    fun searchByName(name: String) {
        viewModelScope.launch {
            _idResult.value = null
            _nameResult.value = JikanRepository.getAnimeByName(name) ?: emptyList()
        }
    }
}
