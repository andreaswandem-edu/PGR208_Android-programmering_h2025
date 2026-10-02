package com.example.eksamen_h2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.eksamen_h2025.data.room.AnimeRepository
import com.example.eksamen_h2025.navigation.AppNavigation
import com.example.eksamen_h2025.screens.animecreate.AnimeCreateViewModel
import com.example.eksamen_h2025.screens.animedetails.AnimeDetailsViewModel
import com.example.eksamen_h2025.screens.animeedit.AnimeEditViewModel
import com.example.eksamen_h2025.screens.animelist.AnimeListViewModel
import com.example.eksamen_h2025.screens.animesearch.AnimeSearchViewModel
import com.example.eksamen_h2025.screens.home.HomeViewModel
import com.example.eksamen_h2025.ui.theme.Eksamen_H2025Theme

class MainActivity : ComponentActivity() {

    // ViewModels
    private val _animeListViewModel: AnimeListViewModel by viewModels()
    private val _animeDetailsViewModel: AnimeDetailsViewModel by viewModels()
    private val _animeSearchViewModel: AnimeSearchViewModel by viewModels()
    private val _homeViewModel: HomeViewModel by viewModels()
    private val _animeCreateViewModel: AnimeCreateViewModel by viewModels()
    private val _animeEditViewModel: AnimeEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AnimeRepository.initializeDatabase(applicationContext)

        enableEdgeToEdge()

        setContent {
            Eksamen_H2025Theme {
                AppNavigation(
                    _animeListViewModel,
                    _animeDetailsViewModel,
                    _animeSearchViewModel,
                    _homeViewModel,
                    _animeCreateViewModel,
                    _animeEditViewModel
                )
            }
        }
    }
}
