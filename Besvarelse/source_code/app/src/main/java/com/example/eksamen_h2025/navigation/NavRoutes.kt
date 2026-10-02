package com.example.eksamen_h2025.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {

    @Serializable
    object AnimeListRoute : NavRoutes()

    @Serializable
    object HomeRoute : NavRoutes()

    @Serializable
    object AnimeSearchRoute : NavRoutes()

    // Denne tilhører db delen
    @Serializable
    object AnimeCreateRoute : NavRoutes()

    @Serializable
    data class AnimeDetailsRoute(
        val animeId: Int
    ) : NavRoutes()

    // Denne tilhører db delen
    @Serializable
    data class AnimeEditRoute(
        val animeId: Int
    ) : NavRoutes()
}

