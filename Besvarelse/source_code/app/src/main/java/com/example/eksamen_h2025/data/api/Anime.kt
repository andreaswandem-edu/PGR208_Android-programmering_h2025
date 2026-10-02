package com.example.eksamen_h2025.data.api

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("mal_id")
    val id: Int,
    val title: String?,
    val images: Images?,
    val score: Double?,
    val synopsis: String?
)

data class Pagination(
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int,
    @SerializedName("current_page")
    val currentPage: Int
)

data class Animes(
    val pagination: Pagination,
    val data: List<Anime>
)

data class AnimeResponse(
    val data: Anime
)

data class Images(
    val jpg: Jpg?
)

data class Jpg(
    val image_url: String?
)