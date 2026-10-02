package com.example.eksamen_h2025.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen_h2025.data.api.Anime
import com.example.eksamen_h2025.data.api.JikanRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var randomAnime by mutableStateOf<Anime?>(null)

    fun getRandomAnime() {
        viewModelScope.launch {
            randomAnime = JikanRepository.getRandomAnime()
        }
    }
}