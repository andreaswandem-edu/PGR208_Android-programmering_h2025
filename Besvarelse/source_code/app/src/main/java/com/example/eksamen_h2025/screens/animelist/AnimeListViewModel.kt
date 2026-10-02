package com.example.eksamen_h2025.screens.animelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen_h2025.data.api.Anime
import com.example.eksamen_h2025.data.api.JikanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {

    private val _animes = MutableStateFlow<List<Anime>>(emptyList())
    val animes = _animes.asStateFlow()
    var currentPage = MutableStateFlow(1)
    var lastPage = MutableStateFlow(1)

    init {
        getAllAnimes()
    }

    fun getAllAnimes(page: Int = currentPage.value) {
        viewModelScope.launch {
            val response = JikanRepository.getAllAnimes(page)

            if ( response != null ) {
                _animes.value = response.data
                currentPage.value = response.pagination.currentPage
                lastPage.value = response.pagination.lastVisiblePage
            }
        }
    }

    fun nextPage() {
        val current = currentPage.value
        val last = lastPage.value

        if ( current < last ) {
            getAllAnimes(page = current + 1)
        }
    }

    fun previousPage() {
        val current = currentPage.value

        if ( current > 1 ) {
            getAllAnimes(page = current - 1)
        }
    }

}
